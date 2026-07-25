#version 150

layout(std140) uniform SkySettings {
    float time;
    float scale;
    int mode;
    vec4 primaryColor;
    vec4 secondaryColor;
    vec4 accentColor;
};

in vec3 skyPosition;
out vec4 fragColor;

float hash3(vec3 p) {
    return fract(sin(dot(p, vec3(127.1, 311.7, 251.9))) * 43758.5453123);
}

float noise3(vec3 p) {
    vec3 i = floor(p);
    vec3 f = fract(p);
    vec3 u = f * f * (3.0 - 2.0 * f);

    float n000 = hash3(i + vec3(0.0, 0.0, 0.0));
    float n100 = hash3(i + vec3(1.0, 0.0, 0.0));
    float n010 = hash3(i + vec3(0.0, 1.0, 0.0));
    float n110 = hash3(i + vec3(1.0, 1.0, 0.0));
    float n001 = hash3(i + vec3(0.0, 0.0, 1.0));
    float n101 = hash3(i + vec3(1.0, 0.0, 1.0));
    float n011 = hash3(i + vec3(0.0, 1.0, 1.0));
    float n111 = hash3(i + vec3(1.0, 1.0, 1.0));

    float nx00 = mix(n000, n100, u.x);
    float nx10 = mix(n010, n110, u.x);
    float nx01 = mix(n001, n101, u.x);
    float nx11 = mix(n011, n111, u.x);
    float nxy0 = mix(nx00, nx10, u.y);
    float nxy1 = mix(nx01, nx11, u.y);
    return mix(nxy0, nxy1, u.z);
}

float fbm3(vec3 p) {
    float value = 0.0;
    float amplitude = 0.5;
    mat3 rot = mat3(
        0.00, 0.80, 0.60,
       -0.80, 0.36, -0.48,
       -0.60, -0.48, 0.64
    );
    for (int i = 0; i < 5; i++) {
        value += noise3(p) * amplitude;
        p = rot * p * 1.92 + vec3(8.7, 4.9, 6.3);
        amplitude *= 0.55;
    }
    return value;
}

float starLayer(vec3 direction, float density, float threshold) {
    vec3 scaled = direction * density;
    vec3 cell = floor(scaled);
    vec3 local = fract(scaled) - 0.5;
    float rnd = hash3(cell);
    float sparkle = hash3(cell + 17.0);
    float radius = mix(0.04, 0.11, sparkle * sparkle);
    float star = smoothstep(radius, 0.0, length(local));
    return star * step(threshold, rnd);
}

float bandPulse(float value, float center, float width) {
    return 1.0 - smoothstep(0.0, width, abs(value - center));
}

vec3 renderPlasma(vec3 direction, float t) {
    vec3 samplePos = direction * (2.8 / max(scale, 0.001));
    vec3 flow = vec3(
        fbm3(samplePos * 0.92 + vec3(t * 0.40, -t * 0.24, t * 0.12)),
        fbm3(samplePos * 0.97 + vec3(-t * 0.21, t * 0.30, -t * 0.09)),
        fbm3(samplePos * 0.88 + vec3(t * 0.14, t * 0.08, -t * 0.26))
    );
    vec3 warp = samplePos + (flow - 0.5) * 1.25;

    float waves = sin(warp.x * 2.2 + t * 1.7);
    waves += sin(warp.y * 2.7 - t * 1.4);
    waves += sin(warp.z * 2.0 + t * 0.95);
    waves += sin(length(warp.xy * vec2(1.05, 1.35)) * 3.8 - t * 1.9);
    float plasma = waves * 0.125 + 0.5;

    float filament = pow(clamp((plasma - 0.62) / 0.38, 0.0, 1.0), 3.2);
    float haze = fbm3(warp * 1.35 + vec3(t * 0.18, -t * 0.12, t * 0.07));
    float glow = smoothstep(-0.35, 0.95, direction.y);

    vec3 base = mix(primaryColor.rgb, secondaryColor.rgb, clamp(plasma * 0.72 + haze * 0.28, 0.0, 1.0));
    vec3 color = base * (0.42 + plasma * 0.32);
    color += accentColor.rgb * filament * 0.72;
    color += secondaryColor.rgb * glow * 0.18;
    color += vec3(1.0) * filament * 0.04;
    return color;
}

vec3 renderLunarisBloom(vec3 direction, float t) {
    vec3 samplePos = direction * (2.0 / max(scale, 0.001));
    vec3 drift = vec3(t * 0.045, -t * 0.020, t * 0.025);

    float mist = fbm3(samplePos * 1.25 + drift);
    float veil = fbm3(samplePos * 2.10 - drift * 0.7);
    float cloud = smoothstep(0.26, 0.92, mist * 0.72 + veil * 0.28);

    vec3 bloomCenter = normalize(vec3(0.18, 0.62, -0.16));
    float bloomDot = clamp(dot(direction, bloomCenter), -1.0, 1.0);
    float bloomAngle = acos(bloomDot);
    float halo = exp(-bloomAngle * 7.5);

    float petalField = sin(atan(direction.z, direction.x) * 5.0 + mist * 4.8 - t * 0.10);
    float petals = pow(max(petalField * 0.5 + 0.5, 0.0), 3.8);
    petals *= 1.0 - smoothstep(0.18, 0.95, bloomAngle);

    float shimmer = starLayer(direction + vec3(0.0, t * 0.0003, 0.0), 34.0, 0.991) * 0.65;
    shimmer += starLayer(direction * 1.5 + vec3(7.4, 2.1, 4.7), 54.0, 0.996) * 0.90;

    vec3 base = mix(primaryColor.rgb, secondaryColor.rgb, clamp(cloud * 0.65 + mist * 0.20, 0.0, 1.0));
    vec3 color = base * (0.48 + cloud * 0.34);
    color += accentColor.rgb * petals * 0.58;
    color += mix(accentColor.rgb, vec3(1.0), 0.45) * halo * 0.42;
    color += secondaryColor.rgb * halo * 0.16;
    color += mix(accentColor.rgb, vec3(1.0), 0.65) * shimmer;
    color += accentColor.rgb * smoothstep(-0.35, 0.95, direction.y) * 0.10;
    return color;
}

vec3 renderVoidCrown(vec3 direction, float t) {
    vec3 samplePos = direction * (2.35 / max(scale, 0.001));
    vec3 crownCenter = normalize(vec3(0.0, 0.20, -1.0));
    float crownDot = clamp(dot(direction, crownCenter), -1.0, 1.0);
    float crownAngle = acos(crownDot);

    float nebula = fbm3(samplePos * 1.05 + vec3(t * 0.060, -t * 0.018, t * 0.022));
    float storm = fbm3(samplePos * 2.25 - vec3(t * 0.090, t * 0.030, -t * 0.040));
    float shadow = smoothstep(0.16, 0.88, nebula * 0.68 + storm * 0.32);

    float ring = bandPulse(crownAngle, 0.78, 0.11);
    float core = 1.0 - smoothstep(0.05, 0.26, crownAngle);
    float azimuth = atan(direction.z - crownCenter.z, direction.x - crownCenter.x + 0.0001);
    float spikes = pow(max(sin(azimuth * 7.0 + t * 0.22) * 0.5 + 0.5, 0.0), 9.0);
    float crown = ring * (0.35 + spikes * 0.95);

    float rift = bandPulse(crownAngle + storm * 0.08, 0.54, 0.06) * smoothstep(0.30, 0.92, nebula);
    float stars = starLayer(direction + vec3(-t * 0.0006, t * 0.0004, 0.0), 30.0, 0.992) * 0.55;
    stars += starLayer(direction * 1.8 + vec3(10.0, 6.0, 2.0), 58.0, 0.997) * 0.95;

    vec3 base = mix(primaryColor.rgb, secondaryColor.rgb, clamp(nebula * 0.74 + storm * 0.26, 0.0, 1.0));
    vec3 color = base * (0.20 + shadow * 0.28);
    color = mix(color, primaryColor.rgb * 0.22, core * 0.92);
    color += accentColor.rgb * crown * 0.46;
    color += mix(accentColor.rgb, vec3(1.0), 0.18) * rift * 0.32;
    color += secondaryColor.rgb * ring * 0.12;
    color += mix(accentColor.rgb, vec3(1.0), 0.55) * stars;
    color += secondaryColor.rgb * smoothstep(-0.50, 0.80, direction.y) * 0.05;
    return color;
}

vec3 renderMist(vec3 direction, float t) {
    vec3 samplePos = direction * (2.5 / max(scale, 0.001));
    float t2 = t * 0.15;

    float f = fbm3(samplePos + vec3(t2 * 0.5));

    vec3 baseColor = mix(primaryColor.rgb, secondaryColor.rgb, clamp(f * 1.5, 0.0, 1.0));
    vec3 color = baseColor * (0.4 + f * 0.6);

    color += accentColor.rgb * pow(f, 3.0) * 0.3;

    float horizon = smoothstep(-0.3, 0.6, direction.y);
    color = mix(color * 0.3, color, horizon);

    float glow = smoothstep(-0.2, 0.9, direction.y);
    color += secondaryColor.rgb * glow * 0.15;

    return color;
}

vec3 renderFractal(vec3 direction, float t) {
    float fixedScale = 2.5;
    vec2 sp = direction.xz / max(direction.y + 0.5, 0.1) * (10.0 / fixedScale) - vec2(10.0);
    vec2 i = sp;
    float c = 1.0;
    float inten = 0.1 / fixedScale;

    for (int n = 0; n < 6; n++) {
        float timeT = t * (1.0 - (3.0 / float(n + 1)));
        i = sp + vec2(cos(timeT - i.x) + sin(timeT + i.y), sin(timeT - i.y) + cos(timeT + i.x));
        c += 1.0 / length(vec2(sp.x / (sin(i.x + timeT) / inten), sp.y / (cos(i.y + timeT) / inten)));
    }

    c /= 6.0;
    c = 1.5 - sqrt(c);
    c = clamp(c, 0.0, 0.8);

    vec3 baseColor = vec3(c * c / 2.0 * c * c);
    vec3 themeColor = mix(primaryColor.rgb, secondaryColor.rgb, 0.5) + accentColor.rgb * 0.5;
    vec3 color = mix(baseColor, themeColor, 0.4);
    color += vec3(1.0) * c * 0.2;

    float horizon = smoothstep(-0.3, 0.5, direction.y);
    color *= 0.5 + horizon * 0.5;

    return color * 1.2;
}

vec3 renderNebula(vec3 direction, float t) {
    float fixedScale = 2.5;
    float t2 = t * 0.12;
    vec2 p = direction.xz / max(direction.y + 0.5, 0.1) / fixedScale;
    p = mod(p * 6.28318530718, 6.28318530718) - 254.0;
    vec2 i = p;
    float c = 1.2;
    float inten = 0.0064 / fixedScale;

    for (int n = 0; n < 4; n++) {
        float timeT = t2 * (1.0 - (7.2 / float(n + 1)));
        i = p + vec2(cos(timeT - i.x) + sin(timeT - i.y), sin(timeT - i.y) + cos(timeT + i.x));
        c += 1.0 / length(vec2(p.x / (sin(i.x + timeT) / inten), p.y / (cos(i.y + timeT) / inten)));
    }

    c /= 4.0;
    c = 1.23 - pow(c, 1.22);

    vec3 baseColor = vec3(0.1 + pow(abs(c), 19.2), 0.15 + pow(abs(c), 35.0), 0.12 + pow(abs(c), 5.0));
    vec3 themeColor = mix(primaryColor.rgb, secondaryColor.rgb, 0.3) + accentColor.rgb * 0.6;
    vec3 color = mix(baseColor, themeColor, 0.35);
    color += vec3(1.0) * abs(c) * 0.3;

    float horizon = smoothstep(-0.3, 0.5, direction.y);
    color *= 0.5 + horizon * 0.5;

    return color * 1.1;
}

void main() {
    vec3 direction = normalize(skyPosition);
    float t = time;

    vec3 color;
    if (mode == 1) {
        color = renderMist(direction, t);
    } else if (mode == 2) {
        color = renderFractal(direction, t);
    } else if (mode == 3) {
        color = renderNebula(direction, t);
    } else {
        color = renderPlasma(direction, t);
    }
    color *= 0.82 + smoothstep(-0.2, 0.85, direction.y) * 0.18;

    fragColor = vec4(color, 1.0);
}
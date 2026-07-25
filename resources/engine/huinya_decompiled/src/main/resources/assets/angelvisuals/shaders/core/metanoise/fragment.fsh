#version 150

#moj_import <angelvisuals:common.glsl>

in vec2 FragCoord;
in vec4 FragColor;

uniform vec2 Size;
uniform float Time;

uniform vec4 BgColor;
uniform vec4 OutlineColor;

uniform vec4 Radius;
uniform float Smoothness;

uniform vec4 ColorModulator;

out vec4 OutColor;

float hash(vec2 p) {
    p = fract(p * vec2(123.34, 456.21));
    p += dot(p, p + 45.32);
    return fract(p.x * p.y);
}

float noise(vec2 p) {
    vec2 i = floor(p);
    vec2 f = fract(p);
    f = f * f * (3.0 - 2.0 * f);

    float a = hash(i);
    float b = hash(i + vec2(1.0, 0.0));
    float c = hash(i + vec2(0.0, 1.0));
    float d = hash(i + vec2(1.0, 1.0));

    return mix(mix(a, b, f.x), mix(c, d, f.x), f.y);
}

float fbm(vec2 p) {
    float v = 0.0;
    float a = 0.5;
    for (int i = 0; i < 5; i++) {
        v += noise(p) * a;
        p *= 2.0;
        a *= 0.5;
    }
    return v;
}

void main() {
    vec2 uv = FragCoord * Size;
    uv /= Size;

    vec2 p = uv * 3.0;

    float randomOffset = fbm(vec2(Time * 0.1, Time * 0.23)) * 3.0;
    float n = fbm(p + randomOffset + Time * 0.3);

    float t = smoothstep(0.0, 1.0, Time);
    float threshold = mix(1.0, 0.0, t);

    float mask = step(threshold, n);
    float dist = abs(n - threshold);
    float lineSmooth = 1.0 - smoothstep(0.0, 0.05, dist);

    vec3 baseArea = BgColor.rgb * mask;
    float areaAlpha = BgColor.a * mask;

    vec3 outline = OutlineColor.rgb * lineSmooth;
    float lineAlpha = OutlineColor.a * lineSmooth;

    vec3 col = baseArea + outline;
    float alpha = areaAlpha + lineAlpha;

    vec2 center = Size * 0.5;

    vec2 pos = center - (FragCoord * Size);

    float shapeDist = roundedBoxSDF(pos, center - 1.0, Radius);

    float roundMask = 1.0 - smoothstep(1.0 - Smoothness, 1.0, shapeDist);

    alpha *= roundMask;
    col *= roundMask;

    if (alpha <= 0.0) discard;

    OutColor = vec4(col, alpha) * ColorModulator;
}
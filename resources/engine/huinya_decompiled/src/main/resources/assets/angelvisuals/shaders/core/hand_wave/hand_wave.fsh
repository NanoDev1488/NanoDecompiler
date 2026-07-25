#version 150

uniform sampler2D Sampler0;
uniform sampler2D Sampler2;
uniform vec4 ColorModulator;
uniform float Time;
uniform vec2 Resolution;
uniform vec4 ThemeColor;
uniform float OutlineWidth;
uniform float GlowStrength;
uniform float FillAmount;
uniform float Alpha;
uniform float WaveSpeed;
uniform float WaveScale;

in vec2 texCoord;
in vec4 vertexColor;
in vec3 worldPos;
in vec3 viewNormal;
in vec3 viewDir;
in vec4 overlayColor;
flat in ivec2 lightCoord;

out vec4 fragColor;

float hash(vec2 p) {
    return fract(sin(dot(p, vec2(127.1, 311.7))) * 43758.5453123);
}

float noise(vec2 p) {
    vec2 i = floor(p);
    vec2 f = fract(p);
    vec2 u = f * f * (3.0 - 2.0 * f);

    float a = hash(i + vec2(0.0, 0.0));
    float b = hash(i + vec2(1.0, 0.0));
    float c = hash(i + vec2(0.0, 1.0));
    float d = hash(i + vec2(1.0, 1.0));

    return mix(mix(a, b, u.x), mix(c, d, u.x), u.y);
}

float fbm(vec2 p) {
    float value = 0.0;
    float amplitude = 0.5;
    float frequency = 1.0;

    for (int i = 0; i < 4; i++) {
        value += noise(p * frequency) * amplitude;
        frequency *= 2.0;
        amplitude *= 0.5;
    }

    return value;
}

void main() {
    vec4 texColor = texture(Sampler0, texCoord);

    vec4 lightColor = texture(Sampler2, clamp(vec2(lightCoord) / 256.0, vec2(0.5 / 16.0), vec2(15.5 / 16.0)));
    vec3 baseColorWithLight = texColor.rgb * lightColor.rgb;

    float waveCoord = texCoord.y * (WaveScale * 8.0) - Time * WaveSpeed * 3.0;

    vec2 noiseCoord = vec2(texCoord.x * 5.0, texCoord.y * 5.0 + Time * WaveSpeed * 0.5);
    float noiseDistort = fbm(noiseCoord) * 1.5;

    float wavePattern = sin(waveCoord + noiseDistort) * 0.5 + 0.5;
    float wavePattern2 = sin(worldPos.y * (WaveScale * 14.0) - Time * WaveSpeed * 4.5 + noiseDistort * 0.8) * 0.5 + 0.5;

    float pattern = wavePattern * 0.6 + wavePattern2 * 0.4;

    float energy = pow(pattern, 3.0) * 2.0;
    vec3 waveColor = mix(ThemeColor.rgb * 0.1, ThemeColor.rgb * 1.3, pattern);
    vec3 baseColor = mix(baseColorWithLight, waveColor, FillAmount);

    float fresnel = 1.0 - max(dot(normalize(viewNormal), normalize(viewDir)), 0.0);
    float edgeGlow = pow(fresnel, 1.0 / max(OutlineWidth, 0.1)) * GlowStrength;

    float pulse = sin(Time * WaveSpeed * 1.5) * 0.1 + 0.9;
    vec3 finalGlow = ThemeColor.rgb * (edgeGlow + energy) * pulse * GlowStrength;

    vec3 finalColor = baseColor + finalGlow;

    finalColor = mix(overlayColor.rgb, finalColor, overlayColor.a);

    float finalAlpha = texColor.a * Alpha * vertexColor.a * ColorModulator.a;

    fragColor = vec4(finalColor * vertexColor.rgb * ColorModulator.rgb, finalAlpha);
}
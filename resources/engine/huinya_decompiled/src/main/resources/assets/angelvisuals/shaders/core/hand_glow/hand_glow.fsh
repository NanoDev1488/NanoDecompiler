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

in vec2 texCoord;
in vec4 vertexColor;
in vec3 worldPos;
in vec3 viewNormal;
in vec3 viewDir;
in vec4 overlayColor;
flat in ivec2 lightCoord;

out vec4 fragColor;

void main() {
    vec4 texColor = texture(Sampler0, texCoord);

    vec4 lightColor = texture(Sampler2, clamp(vec2(lightCoord) / 256.0, vec2(0.5 / 16.0), vec2(15.5 / 16.0)));
    vec3 baseColorWithLight = texColor.rgb * lightColor.rgb;

    vec3 baseColor = mix(baseColorWithLight, ThemeColor.rgb, FillAmount);

    float fresnel = 1.0 - max(dot(normalize(viewNormal), normalize(viewDir)), 0.0);
    float glow = pow(fresnel, 1.0 / max(OutlineWidth, 0.1)) * GlowStrength;

    vec3 glowColor = ThemeColor.rgb * glow;
    vec3 finalColor = baseColor + glowColor;

    finalColor = mix(overlayColor.rgb, finalColor, overlayColor.a);

    float finalAlpha = texColor.a * Alpha * vertexColor.a * ColorModulator.a;

    fragColor = vec4(finalColor * vertexColor.rgb * ColorModulator.rgb, finalAlpha);
}
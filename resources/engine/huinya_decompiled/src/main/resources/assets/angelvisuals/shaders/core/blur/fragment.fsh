#version 150

#moj_import <angelvisuals:common.glsl>

in vec2 FragCoord;
in vec2 TexCoord;
in vec4 FragColor;

uniform sampler2D Sampler0;
uniform vec2 Size;
uniform vec4 Radius;
uniform float Smoothness;
uniform float BlurRadius;

out vec4 OutColor;

const float DPI = 6.28318530718;
const float STEP = DPI / 16.0;

void main() {
    vec2 multiplier = BlurRadius / textureSize(Sampler0, 0);

    vec3 average = texture(Sampler0, TexCoord).rgb;
    for (float d = 0.0; d < DPI; d += STEP) {
        for (float i = 0.2; i <= 1.0; i += 0.2) {
            average += texture(Sampler0, TexCoord + vec2(cos(d), sin(d)) * multiplier * i).rgb;
        }
    }
    average /= 80.0;

    vec4 color = vec4(average, 1.0) * FragColor;
    color.a *= ralpha(Size, FragCoord, Radius, Smoothness);

    if (color.a == 0.0) {
        discard;
    }

    OutColor = color;
}
#version 150

#moj_import <angelvisuals:common.glsl>

in vec2 FragCoord;
in vec4 FragColor;

uniform vec2 Size;
uniform vec4 Radius;
uniform vec2 Smoothness;
uniform float Thickness;
uniform vec4 ColorModulator;

out vec4 OutColor;

void main() {
    vec2 center = Size * 0.5;
    float distance = roundedBoxSDF(center - (FragCoord * Size), center - 1.0, Radius);

    float alpha = smoothstep(1.0 - Thickness - Smoothness.x - Smoothness.y,
        1.0 - Thickness - Smoothness.y, distance);
    alpha *= 1.0 - smoothstep(1.0 - Smoothness.y, 1.0, distance);

    vec4 finalColor = vec4(FragColor.rgb, FragColor.a * alpha);

    if (finalColor.a == 0.0) {
        discard;
    }

    OutColor = finalColor * ColorModulator;
}
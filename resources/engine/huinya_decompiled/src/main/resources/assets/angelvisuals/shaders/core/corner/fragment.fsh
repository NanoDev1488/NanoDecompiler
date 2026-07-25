#version 150

#moj_import <angelvisuals:common.glsl>

in vec2 FragCoord;
in vec4 FragColor;

uniform vec2 Size;
uniform vec4 Radius;
uniform vec2 Smoothness;
uniform float Thickness;
uniform vec4 ColorModulator;
uniform float CornerIndex;

out vec4 OutColor;

void main() {
    vec2 center = Size * 0.5;
    vec2 pixel = FragCoord.xy * Size;

        if (CornerIndex == 0.0) {
            if (pixel.x > center.x || pixel.y > center.y) discard;
        } else if (CornerIndex == 1.0) {
            if (pixel.x < center.x || pixel.y > center.y) discard;
        } else if (CornerIndex == 2.0) {
            if (pixel.x > center.x || pixel.y < center.y) discard;
        } else if (CornerIndex == 3.0) {
            if (pixel.x < center.x || pixel.y < center.y) discard;
        }
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
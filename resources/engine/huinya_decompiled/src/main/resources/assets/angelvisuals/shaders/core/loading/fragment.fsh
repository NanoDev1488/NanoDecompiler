#version 150

#moj_import <angelvisuals:common.glsl>

in vec2 FragCoord;
in vec4 FragColor;

uniform vec2 Size;
uniform vec4 Radius;
uniform float Smoothness;
uniform vec4 ColorModulator;
uniform float Progress;
uniform float StripeWidth;
uniform float Fade;

out vec4 OutColor;

void main() {
    vec2 center = Size * 0.5;
    float distance = roundedBoxSDF(center - (FragCoord * Size), center - 1.0, Radius);
    float alpha = 1.0 - smoothstep(1.0 - Smoothness, 1.0, distance);

    float diag = (FragCoord.x + FragCoord.y) * 0.5;
    float stripeWidth = StripeWidth;
    float fade = Fade;

    float stripeMask = smoothstep(Progress - stripeWidth * 0.5 - fade, Progress - stripeWidth * 0.5, diag)
                     * (1.0 - smoothstep(Progress + stripeWidth * 0.5, Progress + stripeWidth * 0.5 + fade, diag));

    vec4 stripeColor = FragColor * ColorModulator;

    vec4 finalColor = vec4(stripeColor.rgb, stripeColor.a * alpha * stripeMask);

    if (finalColor.a < 0.01) discard;
    OutColor = finalColor;
}
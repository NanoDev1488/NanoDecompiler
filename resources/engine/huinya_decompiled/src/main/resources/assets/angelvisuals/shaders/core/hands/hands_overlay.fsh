#version 150

uniform sampler2D Sampler0;
uniform vec3 color;
uniform float fill;
uniform float alpha;

in vec2 TexCoord;
out vec4 OutColor;

void main() {
    vec2 uv = TexCoord;
    float center = texture(Sampler0, uv).a;
    float inside = smoothstep(0.05, 0.95, center);
    float a = clamp(alpha * inside * fill, 0.0, 1.0);

    if (a <= 0.001) discard;
    OutColor = vec4(color * a, a);
}
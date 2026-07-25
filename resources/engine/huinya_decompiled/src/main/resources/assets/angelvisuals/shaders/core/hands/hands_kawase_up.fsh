#version 150

uniform sampler2D Sampler0;
uniform vec2 uOffset;
uniform vec2 uHalfPixel;
uniform vec2 uSize;
uniform vec3 color;

in vec2 TexCoord;
out vec4 OutColor;

void main() {
    vec2 uv = TexCoord;
    vec2 halfPixel = uHalfPixel * uOffset;

    vec4 sum = texture(Sampler0, uv + vec2(-halfPixel.x * 2.0, 0.0));
    sum += texture(Sampler0, uv + vec2(-halfPixel.x, halfPixel.y)) * 2.0;
    sum += texture(Sampler0, uv + vec2(0.0, halfPixel.y * 2.0));
    sum += texture(Sampler0, uv + vec2(halfPixel.x, halfPixel.y)) * 2.0;
    sum += texture(Sampler0, uv + vec2(halfPixel.x * 2.0, 0.0));
    sum += texture(Sampler0, uv + vec2(halfPixel.x, -halfPixel.y)) * 2.0;
    sum += texture(Sampler0, uv + vec2(0.0, -halfPixel.y * 2.0));
    sum += texture(Sampler0, uv + vec2(-halfPixel.x, -halfPixel.y)) * 2.0;

    vec4 result = sum / 12.0;
    OutColor = vec4(result.rgb * color, result.a);
}
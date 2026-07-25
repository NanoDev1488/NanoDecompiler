#version 150

uniform sampler2D Sampler0;
uniform vec2 uOffset;
uniform vec2 uHalfPixel;
uniform vec2 uSize;

in vec2 TexCoord;
out vec4 OutColor;

void main() {
    vec2 uv = TexCoord;
    vec2 halfPixel = uHalfPixel * uOffset;

    vec4 sum = texture(Sampler0, uv) * 4.0;
    sum += texture(Sampler0, uv - halfPixel);
    sum += texture(Sampler0, uv + halfPixel);
    sum += texture(Sampler0, uv + vec2(halfPixel.x, -halfPixel.y));
    sum += texture(Sampler0, uv - vec2(halfPixel.x, -halfPixel.y));

    OutColor = sum / 8.0;
}
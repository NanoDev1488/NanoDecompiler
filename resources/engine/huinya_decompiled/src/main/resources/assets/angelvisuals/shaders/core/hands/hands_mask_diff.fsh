#version 150

uniform sampler2D Sampler0;
uniform sampler2D Sampler1;
uniform sampler2D Sampler2;
uniform sampler2D Sampler3;

in vec2 TexCoord;
out vec4 OutColor;

void main() {
    vec2 uv = TexCoord;
    float depthBefore = texture(Sampler2, uv).r;
    float depthAfter = texture(Sampler3, uv).r;

    float result = step(0.0001, depthBefore - depthAfter);
    OutColor = vec4(result, result, result, result);
}
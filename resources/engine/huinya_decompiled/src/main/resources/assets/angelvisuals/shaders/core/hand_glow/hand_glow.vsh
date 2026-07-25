#version 150

in vec3 Position;
in vec4 Color;
in vec2 UV0;
in ivec2 UV1;
in ivec2 UV2;
in vec3 Normal;

uniform sampler2D Sampler1;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

out vec2 texCoord;
out vec4 vertexColor;
out vec3 worldPos;
out vec3 viewNormal;
out vec3 viewDir;
out vec4 overlayColor;
flat out ivec2 lightCoord;

void main() {
    vec4 viewPos = ModelViewMat * vec4(Position, 1.0);
    gl_Position = ProjMat * viewPos;
    texCoord = UV0;
    vertexColor = Color;
    worldPos = Position;

    vec3 rawNormal = mat3(ModelViewMat) * Normal;
    viewNormal = length(rawNormal) > 0.001 ? normalize(rawNormal) : vec3(0.0, 0.0, 1.0);
    viewDir = length(viewPos.xyz) > 0.001 ? normalize(-viewPos.xyz) : vec3(0.0, 0.0, 1.0);

    overlayColor = texelFetch(Sampler1, UV1, 0);
    lightCoord = UV2;
}
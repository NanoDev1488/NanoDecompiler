#version 150

#moj_import <angelvisuals:common.glsl>

in vec3 Position;
in vec4 Color;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

out vec2 FragCoord;
out vec2 TexCoord;
out vec4 FragColor;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);

    FragCoord = rvertexcoord(gl_VertexID);
    TexCoord = gl_Position.xy * 0.5 + 0.5;
    FragColor = Color;
}
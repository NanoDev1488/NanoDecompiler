#version 150

in vec3 Position;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

void main() {
    gl_Position = vec4(Position.xy, 0.0, 1.0);
}
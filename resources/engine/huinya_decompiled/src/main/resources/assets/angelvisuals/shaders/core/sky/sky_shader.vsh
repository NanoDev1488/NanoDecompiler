#version 150

layout(std140) uniform DynamicTransforms {
    mat4 ModelViewMat;
    vec4 ColorModulator;
    vec3 ModelOffset;
    mat4 TextureMat;
    float LineWidth;
};

layout(std140) uniform Projection {
    mat4 ProjMat;
};

in vec3 Position;

out vec3 skyPosition;

void main() {
    skyPosition = Position;
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
}
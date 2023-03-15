attribute vec4 a_position;
attribute vec4 a_color;
attribute vec4 a_hdrColor;
attribute vec2 a_texCoord0;
attribute float a_intensity;
uniform mat4 u_projTrans;
varying vec4 v_color;
varying vec4 v_hdrColor;
varying vec2 v_texCoords;
varying float v_intensity;

void main() {
    v_color = a_color;
    v_hdrColor = a_hdrColor;
    v_texCoords = a_texCoord0;
    v_intensity = a_intensity;
    gl_Position = u_projTrans * a_position;
}
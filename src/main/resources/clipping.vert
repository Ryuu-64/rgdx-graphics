attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;
attribute vec2 a_bottomLeft;
attribute vec2 a_topRight;
uniform mat4 u_projTrans;
varying vec4 v_color;
varying vec2 v_texCoords;
varying vec2 v_bottomLeft;
varying vec2 v_topRight;

void main() {
    v_color = a_color;
    v_texCoords = a_texCoord0;
    v_bottomLeft = a_bottomLeft;
    v_topRight = a_topRight;
    gl_Position = u_projTrans * a_position;
}
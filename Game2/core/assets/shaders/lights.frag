uniform vec2 lightLocation;
uniform vec3 lightColor;
uniform float screenHeight;

void main() {
	float distance = length(lightLocation - gl_FragCoord.xy)/5;
	float attenuation = 1.0 / distance;
	vec4 color = vec4(attenuation, attenuation*.5, attenuation*.2, pow(attenuation, 20)) * vec4(lightColor, 3);

	gl_FragColor = color;
}
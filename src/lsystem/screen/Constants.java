	package lsystem.screen;

public class Constants {

	public static final int INITIAL_WIDTH = 600;
	public static final int INITIAL_HEIGHT = 400;
	public static final float TWENTY_FIVE_DEGREES = 25/360f;
	public static final char[] VALID_CHARS = {'=',']','[','.','+','-','X','Y','x','y','0','1','2','3','4','5','6','7','8','9',' '};
	public static final float[] light_0_ambient = {0.01f, 0.01f, 0.01f, 0.01f};
	public static final float[] light_0_diffuse = {1.0f, 1.0f, 1.0f, 1.0f};
	public static final float[] light_0_specular = {1.0f,1.0f, 1.0f, 1.0f};
	public static final float[] material_specular = {0.8f, 0.8f, 0.8f, 0.8f};
	public static final String HELP = "Alphabet{X,Y}\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "Structure d'une requête:\r\n"
			+ "\r\n"
			+ "{\r\n"
			+ "	Axiom = Lettre ;\r\n"
			+ "	1ère règle;\r\n"
			+ "	2nde règle;\r\n"
			+ "	It = Nb d'itérations;\r\n"
			+ "}\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "Il existe 2 lettres d'écriture\r\n"
			+ "\tX -> avance et dessine  d'une unité\r\n"
			+ "\tY -> Ne dessine pas, ne fais rien, permet de controler l'évolution"
			+ "\r\n"
			+ "Les expressions sont de la forme {X = 2X-2Y}\r\n"
			+ "\r\n"
			+ "On pourra notamment changer de direction avec l'alphabet{x,y}\r\n"
			+ "x et y font par défaut une rotation de 25° autour de leur axe mais il est possible de modifier la rotation définit par défaut\r\n"
			+ "Exemple:\r\n"
			+ "\t+ -> 25° sur l'axe de rotation x ( yaw )\r\n"
			+ "\t35x -> 35° sur l'axe de rotation x ( yaw )\r\n"
			+ "\t-16y -> -16° sur l'axe de rotation y ( pitch )\r\n"
			+ "\r\n"
			+ "Ainsi on pourra écrire:\r\n"
			+ "\r\n"
			+ "\tX = X20yY-X\r\n"
			+ "\r\n"
			+ "On ajoute le principe de branches aux expressions, on utilise donc pour ce faire les crochets \"[ ]\".\r\n"
			+ "Elles permettent de controler l'évolution de l'arbre en appliquant la rotation qu'il lui est appliqué à tout\r\n"
			+ "les elements étant à l'intérieur des crochets"
			+ "\r\n"
			+ "Ainsi, dans l'expression:\r\n"
			+ "\r\n"
			+ "\tX = -[XY-X]+Y\r\n"
			+ "\r\n"
			+ "X-YX aura une rotation de 25° appliqué sur l'axe X et -X aura sur rotation de -25° sur l'axe X appliqué uniquement sur lui"
			+ "\r\n";
}


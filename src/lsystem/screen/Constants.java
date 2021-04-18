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
	public static final String HELP = "Alphabet{X,Y,Z}\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "Structure d'une requête:\r\n"
			+ "\r\n"
			+ "{\r\n"
			+ "	Axiom = Lettre ;\r\n"
			+ "	1ère expression ;\r\n"
			+ "	2nde expression ;\r\n"
			+ "	It = Nb d'itérations;\r\n"
			+ "}\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "Une lettre de l'alphabet = avance d'une unité dans une direction.\r\n"
			+ "Exemple:\r\n"
			+ "\r\n"
			+ "	X -> avance d'une unité sur l'axe X\r\n"
			+ "	0.25X -> avance de 0.25 unité sur l'axe X\r\n"
			+ "	12Z -> avance de 12 unités sur l'axe Z\r\n"
			+ "\r\n"
			+ "On peut combiner les directions:\r\n"
			+ "Exemple:\r\n"
			+ "	\r\n"
			+ "	X=Y -> avance d'une unité sur l'axe X et une sur l'axe Y\r\n"
			+ "	2Y3X0.3Z -> avance de deux unités sur l'axe Y,\r\n"
			+ "			de trois unités sur l'axe X\r\n"
			+ "			de 0.3 unités sur l'axe Z.\r\n"
			+ "\r\n"
			+ "Les expressions sont de la forme {X = 2X0.2YZ}\r\n"
			+ "\r\n"
			+ "On pourra changer de direction avec l'alphabet{x,y}\r\n"
			+ "x et y font par défault une rotation de 45° autour de leur axe.\r\n"
			+ "Exemple:\r\n"
			+ "	x = 45°\r\n"
			+ "	2x = 90°\r\n"
			+ "\r\n"
			+ "Ainsi on pourra écrire:\r\n"
			+ "\r\n"
			+ "	X = X2y0.2Z0.2x\r\n"
			+ "\r\n"
			+ "On ajoute le principe d'ouverture et de fermeture des branches aux expressions,\r\n"
			+ "on utilise donc pour ce faire les \"[ ]\".\r\n"
			+ "\r\n"
			+ "Ainsi, dans l'expression:	{X = [X]+Y}\r\n"
			+ "\r\n"
			+ "On exécute d'abord l'instruction entre crochets, puis, une fois son nombre maximum d'itérations\r\n"
			+ "atteint, on ajoutera un mouvement Y.";
}


package lsystem.screen;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Article1.java author: InfoRital
 * 
 * Code source du premier article. Article1 implémente {@link GLEventListener}
 * pour obtenir le mécanisme de callback
 *
 */

public class Jogl implements GLEventListener {

	public static void initialize(String[] args) {
		// Création de la fenêtre
		Frame frame = new Frame("L-système 3D");
		
		// Création du canvas pour dessiner dessus
		GLCanvas canvas = new GLCanvas();
		
		// Nous attachons ensuite le méchanisme de callback à notre surface dessinable
		canvas.addGLEventListener((GLEventListener) new Jogl());
		
		// dessin -> fenêtre
		frame.add(canvas);
		
		// Création de l'animator
		final Animator animator = new Animator(canvas);
		
		// croix rouge = fermeture de la fenêtre
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// Thread pour arrêter l'animator avant la fenêtre
				new Thread(animator::stop).start();
			}
		});

		// Taille de la fenêtre
		frame.setSize(800, 800);
		frame.setVisible(true);

		// Démarrage de l'animator qui va se charger de faire des appels à la méthode
		animator.start();
	}

	// init() sera appelée une fois au début de l'animation. C'est dans cette méthode
	// que nous nous chargerons de toutes les opérations d'initialisation
	public void init(GLAutoDrawable drawable) {
		// GLEventListener renvoie un contexte (drawable) 
		// que nous allons utiliser pour instancier un objet de type GL
		// qui nous permettra d'utiliser les fonctions OpenGL
		GL gl = drawable.getGL();
		// désactiver la synchronisation verticale indépendamment de la plateforme utilisée
		gl.setSwapInterval(1);
	}

	// Appelée que si la fenêtre d'affichage est redimensionnée
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {


	}

	// display() sera appelée en boucle tout au long de l'application par la classe Animator. 
	// C'est dans cette fonction qu'on fera tout ce qui doit être affiché
	public void display(GLAutoDrawable drawable) {
			final GL2 gl = drawable.getGL().getGL2();
			
			gl.glBegin(GL2.GL_LINES);
			// 2eme argument : angle
			gl.glVertex2f(-0.25f, -0.25f);
			gl.glVertex2f(0.5f, 0.15f);
			gl.glEnd();
			
			gl.glBegin(GL2.GL_LINES);
			gl.glVertex2f(0.5f, 0.15f);
			gl.glVertex2f(0.8f, 0.8f);
			gl.glEnd();
			
	}

	/**
	 * displayChanged() est appelée si le mode d'affichage par exemple est modifié.
	 */
	/*
	 * public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
	 * boolean deviceChanged) { }
	 */

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		Jogl jogl = new Jogl();
		jogl.initialize(args);
	}

}

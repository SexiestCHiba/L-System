package jogl;

import java.awt.*;
import java.awt.event.*;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

/**
 * Article1.java author: InfoRital
 * 
 * Code source du premier article. Article1 implémente {@link GLEventListener}
 * pour obtenir le mécanisme de callback
 *
 */

public class Article1 implements GLEventListener {

	public static void main(String[] args) {
		// Création de la fenêtre
		Frame frame = new Frame("Article1");

		// Création du canvas pour dessiner dessus
		GLCanvas canvas = new GLCanvas();

		// Nous attachons ensuite le méchanisme de callback à notre surface dessinable
		canvas.addGLEventListener((GLEventListener) new Article1());

		// dessin -> fenêtre
		frame.add(canvas);

		// Création de l'animator
		final Animator animator = new Animator(canvas);

		// croix rouge = fermeture de la fenêtre
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// Thread pour arrêter l'animator avant la fenêtre
				new Thread(new Runnable() {
					public void run() {
						animator.stop();
						System.exit(0);
					}
				}).start();
			}
		});

		// Taille de la fenêtre
		frame.setSize(300, 300);
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
		GL gl = drawable.getGL();

	}

	// display() sera appelée en boucle tout au long de l'application par la classe Animator. 
	// C'est dans cette fonction qu'on fera tout ce qui doit être affiché
	public void display(GLAutoDrawable drawable) {

		GL gl = drawable.getGL();
		((GL2) gl).glBegin( GL2.GL_LINES );
	      ((GL2) gl).glVertex3f( -0.75f,0f,3f );// 3 units into the window
	      ((GL2) gl).glVertex3f( 0f,-0.75f,3f );
	      ((GL2) gl).glEnd();
	}

	/**
	 * displayChanged() est appelée si le mode d'affichage par exemple est modifié.
	 * Cependant nous n'implémenterons pas cette méthode.
	 */
	/*
	 * public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
	 * boolean deviceChanged) { }
	 */

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

}

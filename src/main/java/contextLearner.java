import java.awt.EventQueue;
import javax.swing.JFrame;
import java.util.*;

import de.daslaboratorium.machinelearning.classifier.Classification;
import de.daslaboratorium.machinelearning.classifier.Classifier;
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class contextLearner {

	private JFrame frame;
	private JTextField text;
	public static Classifier<String, String> bayes;
	/**
	 * Launch the application.
	 */
  public static void main(String[] args) {
    BayseAgent bayseAgent = new BayseAgent();
    bayseAgent.bayes.setMemoryCapacity(90000000);
    bayseAgent.learnByCategory("happy");
    bayseAgent.learnByCategory("abusive");
    bayseAgent.learnByCategory("sad");
    bayseAgent.learnByCategory("compliment");
    bayseAgent.learnByCategory("taunt");

    bayes = bayseAgent.agent();
    
    /*
     * The classifier can learn from classifications that are handed over
     * to the learn methods. Imagin a tokenized text as follows. The tokens
     * are the text's features. The category of the text will either be
     * positive or negative.
     */
    
    /*
     * Now that the classifier has "learned" two classifications, it will
     * be able to classify similar sentences. The classify method returns
     * a Classification Object, that contains the given featureset,
     * classification probability and resulting category.
     */
    
   
    final String[] unknownText1 = "".split("\\s");
    bayes.classify(Arrays.asList(unknownText1)).getCategory();

    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          contextLearner window = new contextLearner();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
	}

	/**
	 * Create the application.
	 */
	public contextLearner() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
  private void initialize() {
    frame = new JFrame();
     
    frame.getContentPane().setBackground(Color.PINK);
    frame.getContentPane().setLayout(null);
    
    text = new JTextField("");
    text.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 16));
    text.setBackground(Color.WHITE);
    text.setBounds(10, 44, 268, 149);
    frame.getContentPane().add(text);
    text.setColumns(10);

    JButton btnGUESS = new JButton("GUESS");
    btnGUESS.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        String textToClasify = text.getText();
//        textToClasify = textToClasify.split("\\s");
        String[] t1 = textToClasify.split("\\s");
//         String textType = bayes.classify(Arrays.asList(t1)).getCategory();
        
        Collection<Classification<String, String>> textType = ((BayesClassifier<String, String>) bayes).classifyDetailed(Arrays.asList(t1));        
        System.out.println(textType);
        JOptionPane.showMessageDialog(frame, "'"+textToClasify +"' Is classified as:"+ textType);
      }
    });
    btnGUESS.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 12));
    btnGUESS.setBackground(Color.LIGHT_GRAY);
    btnGUESS.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {}
    });
    btnGUESS.setBounds(236, 204, 89, 23);
    frame.getContentPane().add(btnGUESS);
    
    JLabel lblPleaseEnterText = new JLabel(" PLEASE ENTER TEXT");
    lblPleaseEnterText.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 12));
    lblPleaseEnterText.setBackground(Color.LIGHT_GRAY);
    lblPleaseEnterText.setBounds(23, 11, 136, 33);
    frame.getContentPane().add(lblPleaseEnterText);
    
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(299, 11, 125, 109);
		frame.getContentPane().add(btnNewButton);
		frame.setBounds(100, 100, 450, 302);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Mian\\Desktop\\Capture.PNG"));
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				text.setText("");
			}
		});
		btnClear.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 12));
		btnClear.setBackground(Color.LIGHT_GRAY);
		btnClear.setBounds(335, 204, 89, 23);
		frame.getContentPane().add(btnClear);
		
	
	}
}

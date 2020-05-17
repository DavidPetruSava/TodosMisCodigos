package layouts;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculadora {

	public static void main(String[] args) {
		marcoCalculadora miMarco = new marcoCalculadora();
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	

}
class marcoCalculadora extends JFrame
{
	public marcoCalculadora()
	{
		setTitle("Calculadora");
		setBounds(300,300,450,300);
		LaminaCalculadora miLamina = new LaminaCalculadora();
		add(miLamina);
		
		
		setVisible(true);
	}
	
}
class LaminaCalculadora extends JPanel
{
	private JPanel pnlBotonera;
	private JTextField txtPantalla;
	private boolean ceroPrincipio = true;
	private double resultado;
	private String operacion="=";
	
	
	public LaminaCalculadora()
	{
		//construimos la pantalla
		BorderLayout miLayoutPantalla = new BorderLayout();
		setLayout(miLayoutPantalla);
		
		txtPantalla = new JTextField("0");
		
		//deshabilitamos la edicion dentro de la caja de texto
		txtPantalla.setEditable(false);
		
		add(txtPantalla, BorderLayout.NORTH);
		
		//nueva lamina
		
		pnlBotonera =new JPanel();
		GridLayout disposicionBotonera = new GridLayout(4,4);
		pnlBotonera.setLayout(disposicionBotonera);
		
		
		ActionListener botonClicado = new escribirNumero();
		ActionListener botonOperacion = new realizarOperacion();
		//agregamos botones
		ponerBoton("7",botonClicado);
		ponerBoton("8",botonClicado);
		ponerBoton("9",botonClicado);
		ponerBoton("/",botonOperacion);
		
		ponerBoton("4",botonClicado);
		ponerBoton("5",botonClicado);
		ponerBoton("6",botonClicado);
		ponerBoton("+",botonOperacion);
		
		ponerBoton("1",botonClicado);
		ponerBoton("2",botonClicado);
		ponerBoton("3",botonClicado);
		ponerBoton("-",botonOperacion);
		
		ponerBoton("0",botonClicado);
		ponerBoton(".",botonClicado);
		ponerBoton("=",botonOperacion);
		ponerBoton("*",botonOperacion);
		
		//agregar la botonera a la lamina ade la calculadora
		add(pnlBotonera, BorderLayout.CENTER);
	}
	
	private void ponerBoton(String text, ActionListener oyente)
	{
		JButton btnCalc = new JButton(text);
		//ponemos a la escucha el boton
		btnCalc.addActionListener(oyente);
		
		pnlBotonera.add(btnCalc);
	}
	private class escribirNumero implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//capturar el texto del botoon pulsado y escribirlo en la panralla de la calculadora
			String textoBoton = e.getActionCommand();
			
			//para concatenar los numeros
			//evitar que haya un 0 a la izquierda
			if(ceroPrincipio)
			{
				txtPantalla.setText("");
				ceroPrincipio=false;
			}
			
			txtPantalla.setText(txtPantalla.getText() + textoBoton);
			
			
		}
		
	}
	
	private class realizarOperacion implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//averiguar si el usuario quiere +,-,/,*,=
			
			double operando =Double.parseDouble(txtPantalla.getText());
			
			calcular(operando);
			operacion=e.getActionCommand();
			
			ceroPrincipio=true;
		}
		
		public void calcular(double num)
		{
			switch(operacion)
			{
			case "+":
				resultado += num;
				break;
			case "-":
				resultado -= num;
				break;
			case "*":
				resultado *= num;
				break;
			case "/":
				resultado /= num;
				break;
			case "=":
				resultado=num;
				break;
			}
			
			DecimalFormat df = new DecimalFormat("###.#");
			txtPantalla.setText("" + df.format(resultado));
		}
	}
	
}
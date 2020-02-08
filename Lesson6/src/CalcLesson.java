import javax.swing.*;
import java.awt.*;

class SimpleJFrame {
    public static void createGUI ()
    {
        JFrame frame = new JFrame("Test frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Test Label");
        frame.getContentPane().add(label);
        frame.setPreferredSize(new Dimension(200, 100));
        frame.pack();
        frame.setVisible(true);
    }
}
class Figure extends JComponent
{
    private Color color;
    private int type; // 0 - круг, 1 - прямоугольник
    private String text;

    // параметры: цвет и тип фигуры
    Figure(Color color, int type, String text) {
        this.color = color;
        this.type = type;
        this.text = text;
        setOpaque(false);
    }

    public void paintComponent(Graphics g) {
        // прорисовка фигуры
        g.setColor(color);
        switch (type) {
            case 0: g.fillOval(0, 0, 90, 90); break;
            case 1: g.fillRect(0, 0, 130, 80); break;
        }
    }
}

class LayeredPane extends JFrame
{
    public LayeredPane()

    {
        // создание окна
        super("Example LayeredTest");

        // выход при закрытии окна
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // определение многослойной панели
        JLayeredPane lp = getLayeredPane();

        // создание трех фигур
        Figure figure1 = new Figure(Color.red , 0, "Figure popup");
        Figure figure2 = new Figure(Color.blue, 0, "Figure 1");
        Figure figure3 = new Figure(Color.cyan, 1, "Figure 2");

        // определение местоположения фигур в окне
        figure1.setBounds(10, 40, 120, 120);
        figure2.setBounds(60, 120, 160, 180);
        figure3.setBounds(90, 55, 250, 180);

        // добавление фигур в различные слои
        lp.add(figure1, JLayeredPane.POPUP_LAYER );
        lp.add(figure2, JLayeredPane.PALETTE_LAYER);
        lp.add(figure3, JLayeredPane.PALETTE_LAYER);

        // смена позиции одной из фигур
        lp.setPosition(figure3, 0);

        // определение размера и открытие окна
        setSize(280, 250);
        setVisible(true);
    }

    public static void main(String[] args)    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new LayeredPane();
    }
}




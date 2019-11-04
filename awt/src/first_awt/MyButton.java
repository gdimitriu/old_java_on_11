package first_awt;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MyButton extends Component implements MouseListener {
    /**flag for focus */
    boolean onFocus;
    /** flag for pressed button */
    boolean pressed;

    /** border dimension for 3d button */
    int gap=5;

    public MyButton(String name) {
        super();
        setName(name);
        addFocusListener(new MyFocusListener());
        addMouseListener(this);
    }

    public void paint(Graphics g) {
        Dimension d = getSize();
        int w = d.width;
        int h = d.height;
        g.setColor(getBackground());
        for(int i = 0; i <=gap; i++) {
            g.fill3DRect(i, i,w-2*i, h-2*i, !pressed);
        }
        g.setColor(Color.BLACK);
        if(onFocus) {
            g.drawRect(gap, gap, w-2*gap, h-2*gap);
        }
        FontMetrics fm = g.getFontMetrics();
        int nameWidth = fm.stringWidth(getName());
        int nameAscent = fm.getMaxAscent();
        int nameHeight = nameAscent+fm.getMaxDescent();
        g.drawString(getName(), (w - nameWidth)/2, (h-nameHeight)/2);
    }

    /** for tab key */
    public boolean isFocusTraversable() {
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        requestFocus();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = true;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    class MyFocusListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            onFocus = true;
            repaint();
        }

        @Override
        public void focusLost(FocusEvent e) {
            onFocus = false;
            repaint();
        }
    }
}

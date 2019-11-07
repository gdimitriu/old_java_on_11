package tests;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;

public class TestScrollPane {
    public static void main(String...args) {
        TestFrame frame = new TestFrame("Window to test ScrollPane");
    }
}

class TestFrame extends Frame {
    private ImageDisplay display;
    private ScrollPane pane;
    int dimX = 500;
    int dimY = 400;
    public TestFrame(String name) {
        super(name);
        class FrameClose extends WindowAdapter {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(new FrameClose());
        //load the image

        Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("test.jpg"));
        display = new ImageDisplay(img);
        pane = new ScrollPane();
        pane.add(display);
        add(pane, BorderLayout.CENTER);
        setSize(dimX,dimY);
        setVisible(true);
    }
}

class ImageDisplay extends Component {
    private Image img;
    private Image offImage;
    private Dimension offDim;
    private Graphics offGraphics;
    boolean imageReady = false;

    public ImageDisplay(Image img) {
        this.img = img;
    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }

    @Override
    public void update(Graphics g) {
        Dimension d = getSize();
        if((offGraphics==null) || (!d.equals(offDim))) {
            offDim = d;
            //create the background image
            offImage = createImage(d.width, d.height);
            //take the graphics from background image
            offGraphics = offImage.getGraphics();
            //draw the background image
            if(imageReady) {
                offGraphics.drawImage(img, 0,0,this);
                getParent().getParent().validate();
            } else {
                offGraphics.drawString("Image is loading...Please wait...", 50, 50);
            }
            g.drawImage(offImage, 0,0, this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(img.getWidth(this), img.getHeight(this));
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h) {
        if(infoflags == ImageObserver.ALLBITS) {
            imageReady = true;
            setSize(new Dimension(img.getWidth(this), img.getHeight(this)));
        }
        return super.imageUpdate(img,infoflags, x,y,w,h);
    }
}
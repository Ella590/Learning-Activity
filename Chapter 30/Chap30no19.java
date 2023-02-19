package pkg30.pkg19;

import javax.swing.*;
import java.awt.*;

public class Chap30no19 extends JApplet {

private static final long serialVersionUID = 1L; 
private SortPanel[] sortPanels = new SortPanel[3]; 
private int size = 50; private int sleepTime = 20;

public Chap30no19() { 
    setLayout(new GridLayout(0, 3, 0, 0)); 
    int[] list = new int[size]; 
    for (int i = 0; i < list.length; i++) { 
        list[i] = i + 1; } 
    for (int i = 0; i < list.length; i++) { 
        int index = (int) (Math.random() * list.length); 
        int temp = list[i]; list[i] = list[index]; list[index] = temp; }

sortPanels[0] = new SelectionSortPanel(" Selection Sort ", list, sleepTime);
sortPanels[1] = new InsertionSortPanel(" Insertion Sort ", list, sleepTime);
sortPanels[2] = new BubbleSortPanel(" Bubble Sort ", list, sleepTime);

for (int i = 0; i < sortPanels.length; i++) { 
    add(sortPanels[i]);
} 
} 

public static void main(String[] args) { 
    JFrame frame = new JFrame("Chap30no19"); 
    JApplet applet = new Chap30no19(); 
    frame.add(applet); 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    frame.pack(); 
    frame.setLocationRelativeTo(null); 
    frame.setVisible(true); }

static abstract class SortPanel extends JPanel implements Runnable { 
    private static final long serialVersionUID = 1L; 
    protected static final int BORDER_WIDTH = 10; 
    private static final Dimension PREFFERED_DIMENSION = new Dimension(340, 340);

protected int size; 
protected int[] list; 
protected int sleepTime; 
private String name;

public SortPanel(String name, int[] list, int sleepTime) { 
    this.name = name;
    this.size = list.length; 
    this.sleepTime = sleepTime; 
    this.list = java.util.Arrays.copyOf(list, size); 
    setBackground(Color.MAGENTA); 
    Thread thread = new Thread(this); 
    thread.start(); 
} 

public Dimension getPreferredSize() { 
    return PREFFERED_DIMENSION; 
} 

protected void paintComponent(Graphics g) { 
        super.paintComponent(g);

g.setColor(Color.WHITE); 
g.drawRect(BORDER_WIDTH, BORDER_WIDTH, getWidth() - 2 * BORDER_WIDTH, getHeight() - 2 * BORDER_WIDTH);

Font nameFont = new Font("Times", Font.BOLD, 16); 
FontMetrics nameFontMetrix = getFontMetrics(nameFont);
g.setColor(Color.MAGENTA); 
g.fillRect((getWidth() - nameFontMetrix.stringWidth(name)) / 2, 0, nameFontMetrix.stringWidth(name), BORDER_WIDTH + nameFontMetrix.getAscent() / 3);

g.setColor(Color.WHITE); 
g.setFont(nameFont); 
g.drawString(name, (getWidth() - nameFontMetrix.stringWidth (name)) / 2, BORDER_WIDTH + nameFontMetrix.getAscent() / 3);

}

public abstract void run(); 
} 

class SelectionSortPanel extends SortPanel { 
    private static final long serialVersionUID = 1L; 
    private int yellowColumn = -1; 
    private int cyanColumn = -1; 
    private int blackColumn = -1;

public SelectionSortPanel(String name, int[] list, int sleepTime) { 
    super(name, list, sleepTime); 
} 

public void run() { 
    try { 
        for (int i = 0; i < list.length - 1; i++) { 
            int currentMinIndex = i; 
            yellowColumn = currentMinIndex; 
            for (int j = i + 1; j < list.length; j++) { 
                cyanColumn = j; repaint(); 
                Thread.sleep(3 * sleepTime); 
                if (list[currentMinIndex] > list[j]) { 
                    currentMinIndex = j; 
                    yellowColumn = currentMinIndex; 
                    repaint(); 
                } 
            }

if (currentMinIndex != i) { 
    int tmp = list[currentMinIndex]; 
    list[currentMinIndex] = list[i]; 
    list[i] = tmp; repaint(); 
    Thread.sleep(4 * sleepTime); 
} 

blackColumn++; repaint(); 
        
} 
        blackColumn++; 
        yellowColumn = -1; 
        cyanColumn = -1; 
    } 
    
    catch (InterruptedException e) { 
    } 
    repaint(); 
} 

protected void paintComponent(Graphics g) { 
    super.paintComponent(g); 
    int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size; int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size; 
    for (int i = (blackColumn == -1 ? 0 : blackColumn); i < list.length; i++) { 
        g.setColor(Color.WHITE); 
        g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] *columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);

        g.setColor(Color.MAGENTA);
        g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] *columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);   
}


for (int i = 0; i <= blackColumn; i++) { 
    g.setColor(Color.BLACK);
    g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);

    g.setColor(Color.MAGENTA);
    g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);   
} 

if(yellowColumn != -1) { 
    g.setColor(Color.YELLOW); 
    g.fillRect(2 * BORDER_WIDTH + columnWidth * yellowColumn, getHeight() - list[yellowColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[yellowColumn] * columnHeight); g.setColor(Color.BLACK);
    g.drawRect(2 * BORDER_WIDTH + columnWidth * yellowColumn, getHeight() - list[yellowColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[yellowColumn] * columnHeight); 
} 
if(cyanColumn != -1) { 
    g.setColor(Color.CYAN); 
    g.fillRect(2 * BORDER_WIDTH + columnWidth * cyanColumn, getHeight() - list[cyanColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[cyanColumn] * columnHeight); 
    g.setColor(Color.MAGENTA); g.drawRect(2 * BORDER_WIDTH + columnWidth * cyanColumn, getHeight() - list[cyanColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[cyanColumn] * columnHeight); 
}
}
}

class InsertionSortPanel extends SortPanel {
    private static final long serialVersionUID = 1L; 
    private int yellowColumn = -1; 
    private int cyanColumn = -1; 
    private int blackColumn = -1;

public InsertionSortPanel(String name, int[] list, int sleepTime) { 
    super(name, list, sleepTime); 
}

public void run() { 
    try { 
        for (int i = 1; i < list.length; i++) { 
            blackColumn = i; Thread.sleep(3 * sleepTime); 
            repaint(); 
            yellowColumn = blackColumn; 
            cyanColumn = -1; int k; 
            for (k = i - 1; k >= 0 && list[k] > list[k + 1]; k--) { 
                yellowColumn = k + 1; 
                cyanColumn = k; 
                repaint(); 
                Thread.sleep(4 * sleepTime); 
                int tmp = list[k + 1]; 
                list[k + 1] = list[k]; 
                list[k] = tmp; 
            } 
                yellowColumn = k + 1; 
                cyanColumn = k; 
                repaint(); }

    yellowColumn = -1;
    cyanColumn = -1; 
    } 
    
    catch (InterruptedException e) { 
    } 
    repaint(); 
}

protected void paintComponent(Graphics g) { 
    super.paintComponent(g); 
    int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size; 
    int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size; 
    for (int i = (blackColumn == -1 ? 0 : blackColumn); i < list.length; i++) { 
        g.setColor(Color.WHITE); 
        g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight); 
        g.setColor(Color.MAGENTA); 
        g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
} 
    for (int i = 0; i <= blackColumn; i++) { 
        g.setColor(Color.BLACK); 
        g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight); 
        g.setColor(Color.MAGENTA); g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
}


if(yellowColumn != -1) { 
    g.setColor(Color.YELLOW);
    g.fillRect(2 * BORDER_WIDTH + columnWidth * yellowColumn, getHeight() - list[yellowColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[yellowColumn] * columnHeight); 
    g.setColor(Color.MAGENTA); 
    g.drawRect(2 * BORDER_WIDTH + columnWidth * yellowColumn, getHeight() - list[yellowColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[yellowColumn] * columnHeight); 
} 
if(cyanColumn != -1) { 
    g.setColor(Color.CYAN); 
    g.fillRect(2 * BORDER_WIDTH + columnWidth * cyanColumn, getHeight() - list[cyanColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[cyanColumn] * columnHeight); 
    g.setColor(Color.MAGENTA); 
    g.drawRect(2 * BORDER_WIDTH + columnWidth * cyanColumn, getHeight() - list[cyanColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[cyanColumn] * columnHeight); 
} 
}
}

class BubbleSortPanel extends SortPanel { 
    private static final long serialVersionUID = 1L; 
    private int yellowColumn = -1; 
    private int cyanColumn = -1; 
    private int blackColumn = -1;

public BubbleSortPanel(String name, int[] list, int sleepTime) { 
    super(name, list, sleepTime); 
}

public void run() { 
    try { 
        boolean needNextPass = true; 
        for (int k = 1; k < list.length && needNextPass; k++) { 
            needNextPass = false; 
            for (int i = 0; i < list.length - k; i++) { 
                yellowColumn = i; cyanColumn = i + 1; 
                repaint(); Thread.sleep(3 * sleepTime); 
                if (list[i] > list[i + 1]) { 
                    yellowColumn = i + 1; 
                    cyanColumn = -1; 
                    int temp = list[i]; 
                    list[i] = list[i + 1]; 
                    list[i + 1] = temp; 
                    repaint(); 
                    Thread.sleep(4 * sleepTime); 
                    needNextPass = true; 
                } 
            } 
            blackColumn = size - k; 
        } 
        blackColumn = 0; 
        yellowColumn = -1; 
        cyanColumn = -1; 
    } 
    catch (InterruptedException e) { 
    } 
    repaint(); 
}

protected void paintComponent(Graphics g) {
super.paintComponent(g); 
int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size; 
int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size; 

for (int i = 0; i < (blackColumn == -1 ? list.length : blackColumn); i++) {
    g.setColor(Color.WHITE);
    g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
    g.setColor(Color.MAGENTA);
    g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);   
} 

if(blackColumn != -1) { 
    for (int i = blackColumn; i < list.length; i++) { 
        g.setColor(Color.BLACK); 
        g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight); 
        g.setColor(Color.MAGENTA); 
        g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
    } 
}

if(yellowColumn != -1) { 
    g.setColor(Color.YELLOW); 
    g.fillRect(2 * BORDER_WIDTH + columnWidth * yellowColumn, getHeight() - list[yellowColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[yellowColumn] * columnHeight); 
    g.setColor(Color.MAGENTA); 
    g.drawRect(2 * BORDER_WIDTH + columnWidth * yellowColumn, getHeight() - list[yellowColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[yellowColumn] * columnHeight); 
} 
if(cyanColumn != -1) { 
        g.setColor(Color.CYAN); 
        g.fillRect(2 * BORDER_WIDTH + columnWidth * cyanColumn, getHeight() - list[cyanColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[cyanColumn] * columnHeight); 
        g.setColor(Color.MAGENTA); 
        g.drawRect(2 * BORDER_WIDTH + columnWidth * cyanColumn, getHeight() - list[cyanColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[cyanColumn] * columnHeight); 
         } 
       } 
    }
}
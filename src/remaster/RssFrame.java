package remaster;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import model.FeedItem;
import rss.RssItem;
import rss.RssParser;
import utils.Utils;

public class RssFrame extends JFrame {

    private JPanel content;

    public static void main(String[] args) {
        RssFrame frame = new RssFrame();
        frame.init(800, 600);
    }

    public void init(int width, int height){
        setSize(width, height);
        setTitle("Rss čtečka");
        setLocationRelativeTo(null); //center
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel controlPanel = new JPanel(new BorderLayout());
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(action -> {
            // načíst feed items
            List<FeedItem> items = Utils.getAllFeeds();
            // upravíme v dialogu
            new TableDialog(items).open();
            // a změny uložit
            Utils.saveAllFeeds(items);
        });
        controlPanel.add(editButton, BorderLayout.WEST);

        add(controlPanel, BorderLayout.NORTH);

        content = new JPanel(new WrapLayout());

        test(); // fixme - bude nutno po přidávání cardviews volat refresh
        // ui refresh v případě po přidání do JScrollPane
        // JScrollPane.doLayout?

        add(new JScrollPane(content), BorderLayout.CENTER);

        setVisible(true);

    }

    private void test(){
        try {
            /*
            URLConnection conn = new URL("").openConnection();
            conn.connect();
            conn.getInputStream();*/
            InputStream is = new FileInputStream(
                    new File("download.xml"));

            List<RssItem> items = new RssParser(is).parseItems();

            for (RssItem item : items) {
                content.add(new CardView(item));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

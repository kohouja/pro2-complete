package remaster;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.FeedItem;
import model.FeedTableModel;
import model.TableModel;

public class TableDialog extends JDialog {

    FeedTableModel model;

    public TableDialog(List<FeedItem> feedItems){
        setModal(true);
        setLayout(new BorderLayout());

        JPanel toolbar = new JPanel();
        // TODO přidat tlačítka na přidávání feedů
        // TODO TextField apod
        JButton finishBtn = new JButton("Dokončit");
        toolbar.add(finishBtn, BorderLayout.EAST);
        add(toolbar, BorderLayout.NORTH);

        model = new FeedTableModel();
        model.setItems(feedItems);
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        finishBtn.addActionListener(action -> {
            setVisible(false);
        });

        pack();
        setLocationRelativeTo(null);
    }

    public void open(){
        // todo v budoucnu navracovat co se tu stalo do frame
        setVisible(true);
    }
}

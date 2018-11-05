package remaster;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.TableModel;

public class TableDialog extends JDialog {

    public TableDialog(){
        setModal(true);
        setLayout(new BorderLayout());

        JPanel toolbar = new JPanel();
        // TODO přidat tlačítka na přidávání feedů
        // TODO TextField apod
        JButton finishBtn = new JButton("Dokončit");
        toolbar.add(finishBtn, BorderLayout.EAST);
        add(toolbar, BorderLayout.NORTH);

        TableModel model = new TableModel();
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

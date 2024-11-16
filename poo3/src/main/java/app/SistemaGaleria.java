package app;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Classe principal com interface gráfica
public class SistemaGaleria extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTextArea reportArea;
    private JProgressBar progressBar;
    private List<Evento> eventos;

    public SistemaGaleria() {
        eventos = new ArrayList<>();
        inicializarEventos();

        setTitle("Sistema de Gerenciamento de Galeria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        criarPainelPrincipal();
        criarPainelRelatorio();

        add(mainPanel);
        setVisible(true);
    }

    private void inicializarEventos() {
        Evento evento1 = new Expressionismo("Exposição Expressionista");
        Evento evento2 = new Renascentismo("Mostra Renascentista");

        Artista artista1 = new Artista("Van Gogh");
        Artista artista2 = new Artista("Leonardo da Vinci");
        Artista artista3 = new Artista("Anita Malfatti");
        Artista artista8 = new Artista("Michelangelo di Lodovico");

        evento1.adicionarArtista(artista1);
        evento1.adicionarArtista(artista3);
        evento2.adicionarArtista(artista2);
        evento2.adicionarArtista(artista8);
        
        
        Local sala1 = new SalaGaleria("Sala 1");
        Local sala2 = new SalaGaleria("Sala 2");

        evento1.setLocal(sala1);
        evento2.setLocal(sala2);

        eventos.add(evento1);
        eventos.add(evento2);
    }

    private void criarPainelPrincipal() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Menu Principal", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 18)));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titulo = new JLabel("Sistema de Gerenciamento de Galeria", 
        SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        JButton btnVisitante = new JButton("Adicionar Visitante");
        btnVisitante.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVisitante.addActionListener(e -> adicionarVisitante());

        JButton btnRelatorio = new JButton("Gerar Relatório");
        btnRelatorio.setFont(new Font("Arial", Font.PLAIN, 16));
        btnRelatorio.addActionListener(e -> {
            gerarRelatorio();
            cardLayout.show(mainPanel, "Relatorio");
        });

        JButton btnSair = new JButton("Sair");
        btnSair.setFont(new Font("Arial", Font.PLAIN, 16));
        btnSair.addActionListener(e -> System.exit(0));

        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridy = 0;
        panel.add(titulo, gbc);

        gbc.gridy = 1;
        panel.add(btnVisitante, gbc);

        gbc.gridy = 2;
        panel.add(btnRelatorio, gbc);

        gbc.gridy = 3;
        panel.add(btnSair, gbc);

        mainPanel.add(panel, "Principal");
    }

    private void criarPainelRelatorio() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Relatório de Visitantes", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 18)));
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(reportArea);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(400, 30));
        progressBar.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton btnSalvar = new JButton("Salvar Relatório");
        btnSalvar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnSalvar.addActionListener(e -> salvarRelatorio());

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVoltar.addActionListener(e -> cardLayout.show(mainPanel, "Principal"));

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(scrollPane)
                .addComponent(progressBar)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalvar)
                        .addComponent(btnVoltar))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(scrollPane)
                .addGap(20)
                .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSalvar)
                        .addComponent(btnVoltar))
        );

        mainPanel.add(panel, "Relatorio");
    }

    private void adicionarVisitante() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Selecione o Evento"));
        ButtonGroup group = new ButtonGroup();
        JPanel eventosPanel = new JPanel();
        eventosPanel.setLayout(new BoxLayout(eventosPanel, BoxLayout.Y_AXIS));

        JRadioButton[] radioButtons = new JRadioButton[eventos.size()];
        for (int i = 0; i < eventos.size(); i++) {
            radioButtons[i] = new JRadioButton(eventos.get(i).getNome());
            radioButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            group.add(radioButtons[i]);
            eventosPanel.add(radioButtons[i]);
        }

        panel.add(eventosPanel);

        int result = JOptionPane.showConfirmDialog(this, panel, "Adicionar Visitante",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            for (JRadioButton rb : radioButtons) {
                if (rb.isSelected()) {
                    String eventoEscolhido = rb.getText();
                    eventos.stream()
                            .filter(evento -> evento.getNome().equals(eventoEscolhido))
                            .findFirst()
                            .ifPresent(Evento::adicionarVisitante);

                    JOptionPane.showMessageDialog(this, "Visitante adicionado ao evento " + eventoEscolhido);
                    break;
                }
            }
        }
    }

    private void salvarRelatorio() {
        new Thread(() -> {
            try {
                JFileChooser fileChooser = new JFileChooser();
                int escolha = fileChooser.showSaveDialog(this);

                if (escolha == JFileChooser.APPROVE_OPTION) {
                    File arquivo = fileChooser.getSelectedFile();
                    progressBar.setValue(0);

                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(10); // Simula um processo de salvamento
                        progressBar.setValue(i);
                    }

                    BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo));
                    writer.write(reportArea.getText());
                    writer.close();

                    JOptionPane.showMessageDialog(this, "Relatório salvo com sucesso!");
                }
            } catch (IOException | InterruptedException e) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar o relatório.");
            }
        }).start();
    }

    private void gerarRelatorio() {
        StringBuilder sb = new StringBuilder();
        for (Evento evento : eventos) {
            sb.append("====================================================\n");
            sb.append(evento.getDetalhes()).append("\n");
            sb.append("Local: ").append(evento.getLocal().getDescricao()).append("\n");
            sb.append("Artistas:\n");
            for (Artista artista : evento.getArtistas()) {
                sb.append(" - ").append(artista.getNome()).append("\n");
            }
            sb.append("Visitantes: ").append(evento.getVisitantes()).append("\n");
            sb.append("====================================================\n\n");
        }
        reportArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaGaleria sistema = new SistemaGaleria();
            sistema.cardLayout.show(sistema.mainPanel, "Principal");
        });
    }
}

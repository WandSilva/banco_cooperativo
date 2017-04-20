package View;


import controller.Facade;
import exeption.ContaNaoEncontradaExcep;
import exeption.NumeroMaxUsuariosExep;
import exeption.SaldoInsuficienteExcep;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by wanderson on 14/04/17.
 * Classe responsável por gerenciar os eventos e atributos da
 * interface gráfica criada usando JavaFx SceneBuilder.
 */


public class FXMLTelaInicialController implements Initializable {

    Facade facade = new Facade();
    File file = new File("logo_banco.png");
    Image image = new Image(file.toURI().toString());

    @FXML
    ImageView visualizadorImagem;

    //---------------------- TELA LOGIN -------------------//
    @FXML
    private AnchorPane telaLogin;
    @FXML
    private Button botaoLogin;
    @FXML
    private TextField textContaLogin;
    @FXML
    private PasswordField textSenhaLogin;
    @FXML
    private Hyperlink labelCadastrese;

    //------------------TELA CADASTRO---------------------//

    @FXML
    private TitledPane telaCadastro;

    @FXML
    private TextField nomeTextCadastro;
    @FXML
    private TextField sobreNomeTextCadastro;
    @FXML
    private TextField registroUnicoTextCadastro;
    @FXML
    private TextField senhaTextCadastro;

    @FXML
    private RadioButton radioU1Cadastro;
    @FXML
    private RadioButton radioU2Cadastro;
    @FXML
    private RadioButton radioC1Cadastro;
    @FXML
    private RadioButton radioC2Cadastro;
    @FXML
    private RadioButton radioAddUsuario1;
    @FXML
    private RadioButton radioAddUsuario2;
    @FXML
    private TextField nomeAddUsuario;
    @FXML
    private TextField sobreNomeAddUsuario;
    @FXML
    private TextField registroAddUsuario;
    @FXML
    private TextField senhaUsuario;

    @FXML
    private Label labelRegistroCadastro;

    // ------------ TELA INICIAL --------------\\
    @FXML
    private AnchorPane telaInicial;
    @FXML
    private Label clienteConectado;
    @FXML
    private Label numeroContaConectada;
    @FXML
    private Label tipoContaConectada;
    @FXML
    private Label labelRegistroAddUsuario;
    @FXML
    private AnchorPane telaDepositar;
    @FXML
    private AnchorPane telaSaldo;

    @FXML
    private AnchorPane telaTransferir;

    @FXML
    private AnchorPane telaAddTitular;
    @FXML
    private Label labelSaldo;
    @FXML
    private TextField valorTextDepositar;
    @FXML
    private TextField valorTextTransferir;
    @FXML
    private TextField contaTextTransferir;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        visualizadorImagem.setImage(image);
        telaLogin.setVisible(true);

        telaCadastro.setVisible(false);
        telaCadastro.setExpanded(false);
        telaCadastro.setAnimated(true);

        telaInicial.setVisible(false);
        telaSaldo.setVisible(false);
        telaTransferir.setVisible(false);
        telaDepositar.setVisible(false);
        telaAddTitular.setVisible(false);

        // radioUsuarioCadastro.setSelected(false);
        //radioContaCadastro.setSelected(false);
        //radioUsuario2Cadastro.setSelected(false);
        //radioConta2Cadastro.setSelected(false);

    }

    @FXML
    public void cadastrar(ActionEvent event) {

        if (!nomeTextCadastro.getText().equals("") && !sobreNomeTextCadastro.getText().equals("") &&
                !registroUnicoTextCadastro.getText().equals("") && !senhaTextCadastro.getText().equals("")) {
            String tipoConta, tipoUsuario;
            if (radioC1Cadastro.isSelected())
                tipoConta = "1";
            else tipoConta = "0";

            if (radioU1Cadastro.isSelected())
                tipoUsuario = "0";
            else tipoUsuario = "1";

            facade.criarUsuario(registroUnicoTextCadastro.getText(), nomeTextCadastro.getText(),
                    sobreNomeTextCadastro.getText(), tipoUsuario);

            String numeroConta = facade.criarConta(senhaTextCadastro.getText(), tipoConta, registroUnicoTextCadastro.getText());
            JOptionPane.showMessageDialog(null, "Conta criada! Numero: " + numeroConta, "Conta criada!", JOptionPane.INFORMATION_MESSAGE);
            fecharTelaCadastro(event);
        } else {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Informações insuficientes!", JOptionPane.WARNING_MESSAGE);
        }
    }

    @FXML
    public void logarConta(ActionEvent event) {
        if (!textContaLogin.getText().equals("") && !textSenhaLogin.equals("")) {
            String validador[] = facade.logarConta(textContaLogin.getText(), textSenhaLogin.getText());

            if (validador[0] == "1") {
                telaLogin.setVisible(false);
                telaCadastro.setVisible(false);
                telaInicial.setVisible(true);
                textSenhaLogin.setText("");
                facade.setContaLogada(textContaLogin.getText());
                clienteConectado.setText("Olá, " + validador[1]);
                numeroContaConectada.setText("Conta: "+textContaLogin.getText());
                if (validador[2].equals("1"))
                    tipoContaConectada.setText("Tipo: Polpança");
                else
                    tipoContaConectada.setText("Tipo: Corrente");

            } else {
                JOptionPane.showMessageDialog(null, "Número da conta ou senha inválido(s).", "Número da conta ou senha inválido(s).", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Você deve informar o número da conta e a senha.", "Informações insuficientes!", JOptionPane.WARNING_MESSAGE);
        }
    }
    @FXML
    public void addTitular(ActionEvent event) {
        String tipo;
        if (radioAddUsuario1.isSelected())
            tipo = "1";
        else
            tipo = "0";

        try {
            facade.addTitular(facade.getContaLogada(), registroAddUsuario.getText(), nomeAddUsuario.getText(),
                    sobreNomeAddUsuario.getText(), tipo, senhaUsuario.getText());
            JOptionPane.showMessageDialog(null, "Novo titular adicionado", "Novo titular adicionado", JOptionPane.INFORMATION_MESSAGE);
            fecharTelAaddTitular();
        } catch (NumeroMaxUsuariosExep numeroMaxUsuariosExep) {
            JOptionPane.showMessageDialog(null, "Você já excedeu o número máximo de titulares(Máximo: 3 titulares)", "Número máximo de titulares excedido", JOptionPane.WARNING_MESSAGE);
            fecharTelAaddTitular();
        }

    }
    @FXML
    public void verSaldo(ActionEvent event) {
        fecharTelaTransferir();
        fecharTelaDepositar();
        fecharTelAaddTitular();
        telaSaldo.setVisible(true);
        String saldo = facade.consultarSaldo(facade.getContaLogada());
        labelSaldo.setText("Saldo: R$" + saldo);
    }

    @FXML
    public void depositar(ActionEvent event) {
        if (!valorTextDepositar.getText().equals("")) {
            facade.depositar(facade.getContaLogada(), valorTextDepositar.getText());
            fecharTelaDepositar();
        } else
            JOptionPane.showMessageDialog(null, "Insira um valor para efetuar o depósito", "Insira um valor!", JOptionPane.WARNING_MESSAGE);
    }

    @FXML
    public void transferir(ActionEvent event) {
        try {
            facade.transferir(facade.getContaLogada(), contaTextTransferir.getText(), valorTextTransferir.getText());
            JOptionPane.showMessageDialog(null, "Tranferencia efetuada com sucesso!", "Transferencia concluída", JOptionPane.INFORMATION_MESSAGE);
            fecharTelaTransferir();
        } catch (SaldoInsuficienteExcep saldoInsuficienteExcep) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente. Seu saldo é: R$" + facade.consultarSaldo(facade.getContaLogada()), "Saldo insuficiente!", JOptionPane.WARNING_MESSAGE);
        } catch (ContaNaoEncontradaExcep contaNaoEncontradaExcep) {
            JOptionPane.showMessageDialog(null, "A conta " + contaTextTransferir.getText() + " não existe", "Conta não encontrada", JOptionPane.WARNING_MESSAGE);
        }
    }

    @FXML
    public void logout() {
        facade.setContaLogada(null);
        telaInicial.setVisible(false);
        fecharTelaTransferir();
        fecharTelaDepositar();
        fecharTelaSaldo();
        fecharTelAaddTitular();
        telaLogin.setVisible(true);
        clienteConectado.setText("");
    }

    @FXML
    public void irTelaCadastro() {
        telaCadastro.setVisible(true);
        telaCadastro.setExpanded(true);
        telaCadastro.setCollapsible(false);
        telaLogin.setVisible(false);
    }

    @FXML
    public void fecharTelaCadastro(ActionEvent event) {
        telaLogin.setVisible(true);
        telaCadastro.setExpanded(false);
        telaCadastro.setCollapsible(false);
        telaCadastro.setVisible(false);
        senhaTextCadastro.setText("");
        registroUnicoTextCadastro.setText("");
        sobreNomeTextCadastro.setText("");
        nomeTextCadastro.setText("");
    }

    @FXML
    public void radioUserCadastro(ActionEvent event) {
        RadioButton radioButton = (RadioButton) event.getSource();
        String radioButtonName = radioButton.getText();

        if (radioButtonName.equals(radioU1Cadastro.getText())) {
            radioU2Cadastro.setSelected(false);
            labelRegistroCadastro.setText("CPF");
        } else if (radioButtonName.equals(radioU2Cadastro.getText())) {
            radioU1Cadastro.setSelected(false);
            labelRegistroCadastro.setText("CNPJ");
        }
    }

    @FXML
    public void radioContaCadastro(ActionEvent event) {
        RadioButton radioButton = (RadioButton) event.getSource();
        String radioButtonName = radioButton.getText();

        if (radioButtonName.equals(radioC1Cadastro.getText())) {
            radioC2Cadastro.setSelected(false);
        } else if (radioButtonName.equals(radioC2Cadastro.getText())) {
            radioC1Cadastro.setSelected(false);
        }
    }

    @FXML
    public void radioButtonAddUserEvent(ActionEvent event) {
        RadioButton radioButton = (RadioButton) event.getSource();
        String radioButtonName = radioButton.getText();

        if (radioButtonName.equals(radioAddUsuario1.getText())) {
            radioAddUsuario2.setSelected(false);
            labelRegistroAddUsuario.setText("CPF");
        } else if (radioButtonName.equals(radioAddUsuario2.getText())) {
            radioAddUsuario1.setSelected(false);
            labelRegistroAddUsuario.setText("CNPJ");
        }
    }

    @FXML
    public void abrirTelaDepositar() {
        fecharTelaTransferir();
        fecharTelaSaldo();
        fecharTelAaddTitular();
        telaDepositar.setVisible(true);
    }

    @FXML
    public void abrirTelaTransferir() {
        fecharTelaDepositar();
        fecharTelaSaldo();
        fecharTelAaddTitular();
        telaTransferir.setVisible(true);
    }

    @FXML
    public void abrirTelaAddTitular() {
        fecharTelaSaldo();
        fecharTelaDepositar();
        fecharTelaTransferir();
        telaAddTitular.setVisible(true);
    }

    public void fecharTelaSaldo() {
        telaSaldo.setVisible(false);
        labelSaldo.setText("");
    }

    public void fecharTelaDepositar() {
        telaDepositar.setVisible(false);
        valorTextDepositar.setText("");
    }

    public void fecharTelaTransferir() {
        telaTransferir.setVisible(false);
        contaTextTransferir.setText("");
        valorTextTransferir.setText("");
    }

    public void fecharTelAaddTitular() {
        telaAddTitular.setVisible(false);
        sobreNomeAddUsuario.setText("");
        nomeAddUsuario.setText("");
        registroAddUsuario.setText("");
        senhaUsuario.setText("");
    }
}

package View;


import controller.Facade;
import exeption.ContaNaoEncontradaExcep;
import exeption.SaldoInsuficienteExcep;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;

import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by wanderson on 14/04/17.
 */


public class FXMLTelaInicialController implements Initializable {

    Facade facade = new Facade();

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
    private RadioButton radioUsuarioCadastro;
    @FXML
    private RadioButton radioUsuario2Cadastro;
    @FXML
    private RadioButton radioContaCadastro;
    @FXML
    private RadioButton radioConta2Cadastro;
    @FXML
    private Button botaoCadastrar;
    @FXML
    private Button botaoCancelarCadastro;

    // ------------ TELA INICIAL --------------\\
    @FXML
    private AnchorPane telaInicial;
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
        telaLogin.setVisible(true);

        telaCadastro.setVisible(false);
        telaCadastro.setExpanded(false);
        telaCadastro.setAnimated(true);

        telaInicial.setVisible(false);
        telaSaldo.setVisible(false);
        telaTransferir.setVisible(false);
        telaDepositar.setVisible(false);
        telaAddTitular.setVisible(false);

        radioUsuarioCadastro.setSelected(true);
        radioContaCadastro.setSelected(true);

    }

    @FXML
    public void logarConta(ActionEvent event) {
        if (!textContaLogin.getText().equals("") && !textSenhaLogin.equals("")) {
            int validador = facade.logarConta(textContaLogin.getText(), textSenhaLogin.getText());
            if (validador == 1) {
                telaLogin.setVisible(false);
                telaCadastro.setVisible(false);
                telaInicial.setVisible(true);
                textSenhaLogin.setText("");
                facade.setContaLogada(textContaLogin.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Número da conta ou senha inválido(s).", "Número da conta ou senha inválido(s).", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Você deve informar o número da conta e a senha.", "Informações insuficientes!", JOptionPane.WARNING_MESSAGE);
        }
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

    public void cadastrar(ActionEvent event) {

        if (!nomeTextCadastro.getText().equals("") && !sobreNomeTextCadastro.getText().equals("") &&
                !registroUnicoTextCadastro.getText().equals("") && !senhaTextCadastro.getText().equals("")) {
            String tipoConta, tipoUsuario;
            if (radioContaCadastro.isSelected())
                tipoConta = "1";
            else tipoConta = "0";

            if (radioUsuarioCadastro.isSelected())
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

    public void radioButtonEvent(ActionEvent event) {
        RadioButton radioButton = (RadioButton) event.getSource();
        String radioButtonName = radioButton.getText();

        if (radioButtonName.equals(radioContaCadastro.getText()))
            radioConta2Cadastro.setSelected(false);
        else if (radioButtonName.equals(radioConta2Cadastro.getText()))
            radioContaCadastro.setSelected(false);
        else if (radioButtonName.equals(radioUsuarioCadastro.getText()))
            radioUsuario2Cadastro.setSelected(false);
        else if (radioButtonName.equals(radioUsuario2Cadastro.getText()))
            radioUsuarioCadastro.setSelected(false);
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
        }
        else
            JOptionPane.showMessageDialog(null, "Insira um valor para efetuar o depósito", "Insira um valor!", JOptionPane.WARNING_MESSAGE);
    }
    @FXML
    public void transferir(ActionEvent event){
        try {
            facade.transferir(facade.getContaLogada(), contaTextTransferir.getText(), valorTextTransferir.getText());
            JOptionPane.showMessageDialog(null, "Tranferencia efetuada com sucesso!", "Transferencia concluída", JOptionPane.INFORMATION_MESSAGE);
            fecharTelaTransferir();
        } catch (SaldoInsuficienteExcep saldoInsuficienteExcep) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente. Seu saldo é: R$"+facade.consultarSaldo(facade.getContaLogada()), "Saldo insuficiente!", JOptionPane.WARNING_MESSAGE);
        } catch (ContaNaoEncontradaExcep contaNaoEncontradaExcep) {
            JOptionPane.showMessageDialog(null, "A conta "+contaTextTransferir.getText()+" não existe", "Conta não encontrada", JOptionPane.WARNING_MESSAGE);
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
    }

    @FXML
    public void abrirTelaDepositar(){
        fecharTelaTransferir();
        fecharTelaSaldo();
        fecharTelAaddTitular();
        telaDepositar.setVisible(true);
    }
    @FXML
    public void abrirTelaTransferir(){
        fecharTelaDepositar();
        fecharTelaSaldo();
        fecharTelAaddTitular();
        telaTransferir.setVisible(true);
    }
    @FXML
    public void abrirTelaAddTitular(){
        fecharTelaSaldo();
        fecharTelaDepositar();
        fecharTelaTransferir();
        telaAddTitular.setVisible(true);
    }

    public void fecharTelaSaldo() {
        telaSaldo.setVisible(false);
        labelSaldo.setText("");
    }

    public void fecharTelaDepositar(){
        telaDepositar.setVisible(false);
        valorTextDepositar.setText("");
    }
    public void fecharTelaTransferir(){
        telaTransferir.setVisible(false);
        contaTextTransferir.setText("");
        valorTextTransferir.setText("");
    }
    public void fecharTelAaddTitular(){
        telaAddTitular.setVisible(false);
    }
}

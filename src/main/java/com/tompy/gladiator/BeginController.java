package com.tompy.gladiator;

import com.tompy.game.AbstractGameController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class BeginController extends AbstractGameController {
    private static BeginController singleton;
    @FXML
    private Button btnStartNext;

    @FXML
    private RadioButton rabCampaign;

    @FXML
    private RadioButton rabSingle;

    @FXML
    private TextField txtStableOwner;

    @FXML
    private TextField txtName;

    @FXML
    private ComboBox<String> cmbOpponentType;

    @FXML
    private ComboBox<String> cmbType;

    public static BeginController get() {
        if (singleton != null) {
            return singleton;
        } else {
            throw new RuntimeException("Gladiator Begin Controller not initialized");
        }
    }

    public void init() {
        txtStableOwner.setDisable(false);
        txtName.setDisable(true);
        cmbOpponentType.setDisable(true);
        cmbType.setDisable(true);
        singleton = this;
        cmbType.getItems().add("Light");
        cmbType.getItems().add("Medium");
        cmbType.getItems().add("Heavy");
        cmbType.getItems().add("Retarius");

        cmbOpponentType.getItems().add("Light");
        cmbOpponentType.getItems().add("Medium");
        cmbOpponentType.getItems().add("Heavy");
        cmbOpponentType.getItems().add("Retarius");
    }

    public void onClickNext(ActionEvent event) {
        System.out.println("onClickNext");
    }

    public void onCmbCampaign(ActionEvent event) {
        txtStableOwner.setDisable(false);
        txtName.setDisable(true);
        cmbOpponentType.setDisable(true);
        cmbType.setDisable(true);
    }

    public void onCmbSingle(ActionEvent event) {
        txtStableOwner.setDisable(true);
        txtName.setDisable(false);
        cmbOpponentType.setDisable(false);
        cmbType.setDisable(false);
    }

    public Button getBtnStartNext() {
        return btnStartNext;
    }

    public RadioButton getRabCampaign() {
        return rabCampaign;
    }

    public RadioButton getRabSingle() {
        return rabSingle;
    }

    public TextField getTxtStableOwner() {
        return txtStableOwner;
    }

    public TextField getTxtName() {
        return txtName;
    }

    public ComboBox<String> getCmbOpponentType() {
        return cmbOpponentType;
    }

    public ComboBox<String> getCmbType() {
        return cmbType;
    }
}

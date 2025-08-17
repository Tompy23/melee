package com.tompy.gladiator;

import com.tompy.game.state.AbstractGameState;

public class BeginStateImpl extends AbstractGameState {

    @Override
    public void process(long l) {
        BeginController controller = BeginController.get();

        if (controller.getRabCampaign().isSelected()) {
            if (!controller.getTxtStableOwner().getText().isEmpty() && controller.getBtnStartNext().isDisabled()) {
                controller.getBtnStartNext().setDisable(false);
            }
        }

        if (controller.getRabSingle().isSelected()) {
            if (!controller.getTxtName().getText().isBlank() && controller.getCmbOpponentType()
                    .getValue() != null && controller.getCmbType().getValue() != null && controller.getBtnStartNext()
                    .isDisabled()) {
                controller.getBtnStartNext().setDisable(false);
            }

        }
    }
}

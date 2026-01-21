package iu.SwingStyle.LCCA.Utils.JsonManagerWithConflictSafe;

import java.awt.*;

public interface JsonManagerCallback {

    Component getFrame();


    void actionAfterSuccessfullyOpeningJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe);

    void actionAfterFailingToOpenJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe);

    void actionAfterSuccessfullySavingJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe);

    void actionAfterFailingToSaveJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe);

    void actionAfterSuccessfullyReloadingJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe);

    void actionAfterFailingToReloadJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe);
}

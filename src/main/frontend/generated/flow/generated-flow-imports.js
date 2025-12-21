import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/login/src/vaadin-login-form.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/combo-box/src/vaadin-combo-box.js';
import 'Frontend/generated/jar-resources/flow-component-renderer.js';
import 'Frontend/generated/jar-resources/comboBoxConnector.js';
import '@vaadin/tooltip/src/vaadin-tooltip.js';
import '@vaadin/button/src/vaadin-button.js';
import 'Frontend/generated/jar-resources/buttonFunctions.js';
import '@vaadin/dialog/src/vaadin-dialog.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/multi-select-combo-box/src/vaadin-multi-select-combo-box.js';
import '@vaadin/checkbox/src/vaadin-checkbox.js';
import '@vaadin/notification/src/vaadin-notification.js';
import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/vaadin-lumo-styles/color-global.js';
import '@vaadin/vaadin-lumo-styles/typography-global.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === 'ef1ddf782516dd3f8af8404477a15fc718462c846e7a6d3d64009c2c07c4f255') {
    pending.push(import('./chunks/chunk-b9ce13c32f0faa955de6c05f67c3062c32c65cfa8b286e38f0fb34cb401b7de7.js'));
  }
  if (key === '8ce84d05b717f5deb5310165ad8b654536098a55b05b4b2881bfbf3cc6f647e5') {
    pending.push(import('./chunks/chunk-b9ce13c32f0faa955de6c05f67c3062c32c65cfa8b286e38f0fb34cb401b7de7.js'));
  }
  if (key === '7a93ac4fe6507cfd379538b4c88fe81cad0467c49ec893b2b3a064e11505b3f7') {
    pending.push(import('./chunks/chunk-e0b3cd6c88e81ecf31f8c0af1ffc0e8b7eed11dd6d0d353ee8cc249489c7f421.js'));
  }
  if (key === 'eed1d17758707ff123eaa40bf0883fecea483ba348a0cb32f2d8c2cccaa6c14b') {
    pending.push(import('./chunks/chunk-91b1128ec91a2cd4e058b83c6ae9123d4b21893ecb8a35a11fe08471e1a11fe1.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;
window.Vaadin.Flow.resetFocus = () => {
 let ae=document.activeElement;
 while(ae&&ae.shadowRoot) ae = ae.shadowRoot.activeElement;
 return !ae || ae.blur() || ae.focus() || true;
}
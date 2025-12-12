import '@vaadin/tooltip/src/vaadin-tooltip.js';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/dialog/src/vaadin-dialog.js';
import 'Frontend/generated/jar-resources/flow-component-renderer.js';
import '@vaadin/button/src/vaadin-button.js';
import 'Frontend/generated/jar-resources/buttonFunctions.js';
import '@vaadin/notification/src/vaadin-notification.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === 'eed1d17758707ff123eaa40bf0883fecea483ba348a0cb32f2d8c2cccaa6c14b') {
    pending.push(import('./chunks/chunk-91b1128ec91a2cd4e058b83c6ae9123d4b21893ecb8a35a11fe08471e1a11fe1.js'));
  }
  if (key === '8ce84d05b717f5deb5310165ad8b654536098a55b05b4b2881bfbf3cc6f647e5') {
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
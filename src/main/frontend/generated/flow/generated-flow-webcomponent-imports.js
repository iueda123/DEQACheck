import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/login/src/vaadin-login-form.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/combo-box/src/vaadin-combo-box.js';
import 'Frontend/generated/jar-resources/flow-component-renderer.js';
import 'Frontend/generated/jar-resources/comboBoxConnector.js';
import '@vaadin/accordion/src/vaadin-accordion.js';
import '@vaadin/tooltip/src/vaadin-tooltip.js';
import '@vaadin/button/src/vaadin-button.js';
import 'Frontend/generated/jar-resources/buttonFunctions.js';
import '@vaadin/details/src/vaadin-details.js';
import '@vaadin/dialog/src/vaadin-dialog.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/accordion/src/vaadin-accordion-panel.js';
import '@vaadin/multi-select-combo-box/src/vaadin-multi-select-combo-box.js';
import '@vaadin/checkbox/src/vaadin-checkbox.js';
import '@vaadin/text-area/src/vaadin-text-area.js';
import '@vaadin/notification/src/vaadin-notification.js';
import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === 'ef1ddf782516dd3f8af8404477a15fc718462c846e7a6d3d64009c2c07c4f255') {
    pending.push(import('./chunks/chunk-b9ce13c32f0faa955de6c05f67c3062c32c65cfa8b286e38f0fb34cb401b7de7.js'));
  }
  if (key === 'd860dfc47b9c21b2ca7f5ceb2422254876bc60e5c3a8fed8a89ef03438bc5b25') {
    pending.push(import('./chunks/chunk-fcf0d65f927e73f86d4776007e982cb7a2e26591d132ad4b8ae814aa8a0b2cd8.js'));
  }
  if (key === '33679d077746827effafd0f3e02b8cb54919868182898504b173ed271158fed4') {
    pending.push(import('./chunks/chunk-b441860ca7074a991869a4c503f0cea85b8650aac06275efc8e4287471776857.js'));
  }
  if (key === 'c2f9377fa29f069bd38f23be1b0ab2d0c06a10d2d671d07037a23eb28323817d') {
    pending.push(import('./chunks/chunk-dccec546b00c0a8ddf2e18ee84da981d6e8feeb6c4b67e7dec64543ecb63c8e4.js'));
  }
  if (key === 'fcd165bc197cc43ed447e0a404400d39236c5d4b0234025a54a31c554b94fdcc') {
    pending.push(import('./chunks/chunk-e0b3cd6c88e81ecf31f8c0af1ffc0e8b7eed11dd6d0d353ee8cc249489c7f421.js'));
  }
  if (key === '6f5f78f2b6c5792d981fa0a0db944dc76a06c71d7f528f3ecc00dc475f7e2a6e') {
    pending.push(import('./chunks/chunk-dccec546b00c0a8ddf2e18ee84da981d6e8feeb6c4b67e7dec64543ecb63c8e4.js'));
  }
  if (key === 'eed1d17758707ff123eaa40bf0883fecea483ba348a0cb32f2d8c2cccaa6c14b') {
    pending.push(import('./chunks/chunk-91b1128ec91a2cd4e058b83c6ae9123d4b21893ecb8a35a11fe08471e1a11fe1.js'));
  }
  if (key === '8ce84d05b717f5deb5310165ad8b654536098a55b05b4b2881bfbf3cc6f647e5') {
    pending.push(import('./chunks/chunk-b9ce13c32f0faa955de6c05f67c3062c32c65cfa8b286e38f0fb34cb401b7de7.js'));
  }
  if (key === 'eefcdedb04c3cc8ab6881d9f4260f4b1da2784cbca344dea6c847ef4de7e20b9') {
    pending.push(import('./chunks/chunk-8d29682433b359855574f67d69436e46c0ca1984f9baae926a34fb0057da3af0.js'));
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
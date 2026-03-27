package iu.SpringBoot.Vaadin;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Inline;
import com.vaadin.flow.component.page.TargetElement;
import com.vaadin.flow.server.AppShellSettings;

public class AppShell implements AppShellConfigurator {

    @Override
    public void configurePage(AppShellSettings settings) {
        settings.addInlineWithContents(
                TargetElement.HEAD,
                Inline.Position.PREPEND,
                """
                        (() => {
                          const scrollKey = `deqa:scroll:${location.pathname}`;
                          const lastReloadKey = 'deqa:last-reload';

                          const saveScroll = () => {
                            try {
                              sessionStorage.setItem(scrollKey, String(window.scrollY || 0));
                            } catch (e) {
                            }
                          };

                          const restoreScroll = () => {
                            try {
                              const raw = sessionStorage.getItem(scrollKey);
                              if (raw == null) {
                                return;
                              }
                              const y = Number(raw || '0');
                              if (!Number.isFinite(y)) {
                                return;
                              }
                              window.scrollTo(0, y);
                            } catch (e) {
                            }
                          };

                          try {
                            history.scrollRestoration = 'manual';
                          } catch (e) {
                          }

                          window.addEventListener('scroll', saveScroll, { passive: true });
                          window.addEventListener('pagehide', saveScroll);
                          window.addEventListener('beforeunload', () => {
                            saveScroll();
                            try {
                              sessionStorage.setItem(lastReloadKey, JSON.stringify({
                                at: new Date().toISOString(),
                                path: location.pathname,
                                href: location.href
                              }));
                            } catch (e) {
                            }
                          });

                          window.addEventListener('pageshow', () => {
                            requestAnimationFrame(restoreScroll);
                            setTimeout(restoreScroll, 50);
                            setTimeout(restoreScroll, 250);
                          });

                          const wireNoteArea = (el) => {
                            if (!el || el.dataset.deqaWired === 'true') {
                              return;
                            }
                            const storageKey = el.dataset.noteStorageKey;
                            if (!storageKey) {
                              return;
                            }
                            el.dataset.deqaWired = 'true';

                            const fullKey = `deqa:note:${location.pathname}:${storageKey}`;
                            try {
                              const saved = sessionStorage.getItem(fullKey);
                              if (saved != null && el.value !== saved) {
                                el.value = saved;
                              }
                            } catch (e) {
                            }

                            el.addEventListener('input', () => {
                              try {
                                sessionStorage.setItem(fullKey, el.value || '');
                              } catch (e) {
                              }
                            });
                            el.addEventListener('value-changed', () => {
                              try {
                                sessionStorage.setItem(fullKey, el.value || '');
                              } catch (e) {
                              }
                            });
                          };

                          const wireAll = () => {
                            document.querySelectorAll('vaadin-text-area[data-note-storage-key]')
                              .forEach(wireNoteArea);
                          };

                          const observer = new MutationObserver(() => {
                            wireAll();
                          });

                          const start = () => {
                            wireAll();
                            restoreScroll();
                            observer.observe(document.documentElement, {
                              childList: true,
                              subtree: true
                            });
                          };

                          if (document.readyState === 'loading') {
                            document.addEventListener('DOMContentLoaded', start, { once: true });
                          } else {
                            start();
                          }
                        })();
                        """,
                Inline.Wrapping.JAVASCRIPT
        );
    }
}

(defproject {{raw-name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.854"]
                 [org.clojure/core.async "0.3.443"]
                 [environ "1.1.0"]
                 [ring "1.6.2"]
                 [ring/ring-defaults "0.3.1"]
                 [compojure "1.6.0"]
                 [hiccup "1.0.5"]
                 [reagent "0.7.0"]
                 [reagent-utils "0.2.1"]]
  :plugins [[lein-environ "1.0.2"]
            [lein-cljfmt "0.5.7"]
            [lein-cljsbuild "1.1.7"]
            [lein-doo "0.1.7"]
            [lein-figwheel "0.5.12"]
            [lein-less "1.7.5"]
            [lein-asset-minifier "0.4.3"]]
  :global-vars {*warn-on-reflection* true}
  :cljfmt {}
  :ring {:server {{namespace}}.handler/app
         :init {{namespace}}.handler/init
         :uberwar-name "{{namespace}}.war"}
  :jvm-opts ["-server" "-Djava.awt.headless=true" "-XX:-OmitStackTraceInFastThrow"]
  :uberjar-name "{{namespace}}.jar"
  :main ^:skip-aot {{namespace}}.server
  :source-paths ["src/clj" "src/cljc"]
  :target-path "target/%s"
  :resource-paths ["resources"]
  :clean-targets ^{:protect false} [:target-path
                                    [:cljsbuild :builds :dev :compiler :output-dir]
                                    [:cljsbuild :builds :dev :compiler :output-to]]
  :cljsbuild  {:builds {:prod {:source-paths ["src/cljs" "src/cljc"]
                               :jar true
                               :compiler {:output-to "resources/public/js/app.js"
                                          :output-dir "resources/public/js/prod"
                                          :optimizations :advanced
                                          :pretty-print false
                                          :language-in :ecmascript5
                                          :language-out :ecmascript5}}
                        :dev {:source-paths ["src/cljs" "src/cljc"]
                              :figwheel {:on-jsload "{{namespace}}.app/init!"}
                              :compiler {:main "{{namespace}}.app"
                                         :asset-path "/js/out"
                                         :output-to "resources/public/js/app.js"
                                         :output-dir "resources/public/js/dev"
                                         :source-map true
                                         :optimizations :none
                                         :pretty-print true
                                         :language-in :ecmascript5
                                         :language-out :ecmascript5
                                         :preloads [devtools.preload]}}
                        :test {:source-paths ["src/cljs" "src/cljc" "test/cljs"]
                               :compiler {:main {{namespace}}.test-runner
                                          :asset-path "/js/out"
                                          :output-to "target/test.js"
                                          :output-dir "target/out"
                                          :optimizations :none
                                          :pretty-print true
                                          :language-in :ecmascript5
                                          :language-out :ecmascript5}}}}
  :less {:source-paths ["src/less"]
         :target-path "resources/public/css"}
  :minify-assets [[:css {:source "resources/public/css/styles.css"
                         :target "resources/public/css/styles.min.css"}]]
  :hooks [minify-assets.plugin/hooks
          leiningen.cljsbuild]
  :figwheel {:http-server-root "public"
             :server-port 3449
             :server-ip "127.0.0.1"
             :ring-handler {{namespace}}.handler/app
             :css-dirs ["resources/public/css"]}
  :profiles  {:dev {:dependencies [[binaryage/devtools "0.9.4"]
                                   [figwheel-sidecar "0.5.12"]
                                   [com.cemerick/piggieback "0.2.2"]
                                   [org.clojure/tools.namespace "0.2.11"]]
                    :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                    :env {:dev true}}
              :uberjar {:env {:production true}
                        :aot :all
                        :omit-source true}})

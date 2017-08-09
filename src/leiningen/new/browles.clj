(ns leiningen.new.browles
  (:require [leiningen.core.main :as main]
            [leiningen.new.templates :as tmpl]))

(def render (tmpl/renderer "browles"))

(defn template-data [name]
  (let [ns (tmpl/sanitize-ns name)]
    {:raw-name name
     :name (tmpl/project-name name)
     :namespace ns
     :top-dir (tmpl/name-to-path ns)
     :nested-dirs (tmpl/name-to-path ns)
     :year (tmpl/year)
     :date (tmpl/date)}))

(defn browles [name]
  (let [data (template-data name)]
    (main/info "Generating new Clojure library project:" name)
    (tmpl/->files
     data
     ["project.clj" (render "project.clj" data)]
     ["README.md" (render "README.md" data)]
     [".gitignore" (render "gitignore" data)]
     ["src/clj/{{nested-dir}}/handler.clj" (render "handler.clj" data)]
     ["src/clj/{{nested-dir}}/server.clj" (render "server.clj" data)]
     ["src/cljs/{{nested-dir}}/app.cljs" (render "app.cljs" data)]
     ["src/less/styles.less" (render "styles.less" data)]
     ["src/cljc/{{nested-dir}}/utils.cljc" (render "utils.cljc" data)]
     ["test/clj/{{nested-dir}}_test.clj" (render "test.clj" data)]
     ["test/cljs/{{nested-dir}}_test.cljs" (render "test.cljs" data)]
     ["test/cljc/{{nested-dir}}_test.cljc" (render "test.cljc" data)]
     ["test/{{nested-dir}}/test_runner.cljs" (render "test_runner.cljs" data)]
     "resources"
     "target")))

(ns {{namespace}}.app
  (:require [reagent.core :as reagent]))

(defn main-view []
  [:div])

(defn mount-root []
  (reagent/render [main-view] (.getElementById js/document "app")))

(defn init! []
  (mount-root))

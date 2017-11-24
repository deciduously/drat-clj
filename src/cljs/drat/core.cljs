(ns drat.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [clojure.string :as str]))

;; - EVENT HANDLERS

(rf/reg-event-db
 :initialize
 (fn [_ _]
   {:date (js/Date.)}))

;; - QUERY

(rf/reg-sub
 :date
 (fn [db _]
   (:date db)))

;; - VIEW

(defn ui
  []
  [:div>h1
   (-> @(rf/subscribe [:date])
   .toTimeString
   (str/split " ")
   first)])

;; - ENTRY

(when-let [element (.getElementById js/document "app")]
  (rf/dispatch-sync [:initialize])
  (reagent/render [ui] element))

(def shape-scorecard '{:X 1 :Y 2 :Z 3})

(defn round-to-keywords [round]
  (->> (clojure.string/split round #" ") (map keyword)))

(def input-data
  (let [raw-input (-> (slurp "src/day-2/input.txt") (clojure.string/split #"\n"))]
    (map round-to-keywords raw-input)))

(defn outcome-score [opponent-choice player-choice]
  (case opponent-choice
    :A (player-choice '{:X 3 :Y 6 :Z 0})
    :B (player-choice '{:X 0 :Y 3 :Z 6})
    :C (player-choice '{:X 6 :Y 0 :Z 3})))

;; part 1
(defn part-1-round-score [opponent-choice player-choice]
  (reduce + [(outcome-score opponent-choice player-choice) (get shape-scorecard player-choice)]))

;; part 2
(defn stategy-move->player-choice [opponent-choice stategy-move]
  (case opponent-choice
    :A (stategy-move '{:X :Z :Y :X :Z :Y})
    :B (stategy-move '{:X :X :Y :Y :Z :Z})
    :C (stategy-move '{:X :Y :Y :Z :Z :X})))

(defn part-2-round-score [opponent-choice strategy-move]
  (let [player-choice (stategy-move->player-choice opponent-choice strategy-move)]
    (reduce + [(outcome-score opponent-choice player-choice) (get shape-scorecard player-choice)])))

;; Calculate total score
(defn calculate-game-score
  "Calculate the total game score given a round-score fn - e.g. part-1-round-score"
  [round-score-fn]
  (->> (map #(round-score-fn (first %) (last %)) input-data)
       (reduce +)))
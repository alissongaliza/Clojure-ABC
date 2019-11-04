(ns my-api.schemas
  (:require [schema.core :as s]))


(s/defschema Recipe
             {:id                    s/Int
              :title                 s/Str
              :image                 s/Str
              :imageType             "jpg"
              :likes                 s/Int
              :missedIngredientCount s/Int
              :missedIngredients     [{:name s/Str}]
              :usedIngredientCount   s/Int
              :usedIngredients       [{:name s/Str}]
              })

(s/defschema Ingredient
             {:name s/Str
              })


(s/defschema Fridge
             {:ingredients [Ingredient]
              })


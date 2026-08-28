# https://github.com/huggingface/transformers#quickstart

import os

from transformers import pipeline

current_dir = os.path.dirname(os.path.abspath(__file__))
img_path = f"{current_dir}/../pokemon.png"


def test_image_classification():
    pipe = pipeline(task="image-classification", model="skshmjn/Pokemon-classifier-gen9-1025")
    predictions = pipe(img_path)

    for pred in predictions:
        print(f"pokemon name: {pred['label']}, score: {pred['score']:.2%}")

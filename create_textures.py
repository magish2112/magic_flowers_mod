#!/usr/bin/env python3
"""
Скрипт для создания плейсхолдеров текстур для новых предметов мода
"""

from PIL import Image, ImageDraw, ImageFont
import os

def create_texture(name, color, symbol):
    """Создает простую текстуру для предмета"""
    # Создаем изображение 16x16 (стандарт для Minecraft)
    img = Image.new('RGBA', (16, 16), (0, 0, 0, 0))
    draw = ImageDraw.Draw(img)

    # Рисуем круг с цветом
    draw.ellipse([2, 2, 14, 14], fill=color)

    # Добавляем символ в центр
    try:
        # Пытаемся использовать стандартный шрифт
        font = ImageFont.truetype("arial.ttf", 10)
    except:
        # Если шрифт недоступен, используем дефолтный
        font = ImageFont.load_default()

    # Получаем размеры текста
    bbox = draw.textbbox((0, 0), symbol, font=font)
    text_width = bbox[2] - bbox[0]
    text_height = bbox[3] - bbox[1]

    # Центрируем текст
    x = (16 - text_width) // 2
    y = (16 - text_height) // 2

    # Рисуем текст (белый цвет)
    draw.text((x, y), symbol, fill=(255, 255, 255, 255), font=font)

    return img

def main():
    """Создание текстур для всех новых предметов"""
    textures_dir = "src/main/resources/assets/magicflowers/textures/item"
    os.makedirs(textures_dir, exist_ok=True)

    # Новые текстуры для создания
    new_textures = {
        'golden_tulip.png': ('#FFD700', '🌷'),  # Золотой цвет
        'crystal_rose.png': ('#FF1493', '💎'),  # Розовый + кристалл
        'shadow_orchid.png': ('#4B0082', '🌑'),  # Темно-фиолетовый
        'storm_lily.png': ('#00BFFF', '⚡'),    # Голубой + молния
        'sunflower.png': ('#FFA500', '☀️'),    # Оранжевый + солнце
        'ice_carnation.png': ('#87CEEB', '❄️'), # Светло-голубой + снег
        'power_bouquet.png': ('#FF4500', '🔥'), # Красный + огонь
    }

    print("🎨 Создание текстур для новых предметов...")

    for filename, (color, symbol) in new_textures.items():
        filepath = os.path.join(textures_dir, filename)
        if not os.path.exists(filepath):
            try:
                img = create_texture(filename, color, symbol[0])  # Берем первый символ эмодзи
                img.save(filepath)
                print(f"✅ Создана текстура: {filename}")
            except Exception as e:
                print(f"❌ Ошибка создания {filename}: {e}")
        else:
            print(f"⏭️  Текстура уже существует: {filename}")

    print("\n🎨 Создание моделей предметов...")

    # Создаем модели для новых предметов
    models_dir = "src/main/resources/assets/magicflowers/models/item"
    os.makedirs(models_dir, exist_ok=True)

    # Модель для новых предметов (простая item модель)
    item_model = {
        "parent": "item/generated",
        "textures": {
            "layer0": "magicflowers:item/%s"
        }
    }

    new_models = [
        'golden_tulip',
        'crystal_rose',
        'shadow_orchid',
        'storm_lily',
        'sunflower',
        'ice_carnation',
        'power_bouquet'
    ]

    for model_name in new_models:
        model_file = os.path.join(models_dir, f"{model_name}.json")
        if not os.path.exists(model_file):
            model_data = item_model.copy()
            model_data["textures"]["layer0"] = f"magicflowers:item/{model_name}"

            import json
            with open(model_file, 'w', encoding='utf-8') as f:
                json.dump(model_data, f, indent=2)

            print(f"✅ Создана модель: {model_name}.json")
        else:
            print(f"⏭️  Модель уже существует: {model_name}.json")

    print("\n✨ Все текстуры и модели созданы!")
    print("📝 Примечание: Это плейсхолдеры. Для финальной версии замените на качественные текстуры.")

if __name__ == '__main__':
    main()

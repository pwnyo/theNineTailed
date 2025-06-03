Hi, thanks for your interest in helping translate this mod! I really appreciate it.

The files that need to be translated are in this folder: `localization/zhs`. You can also reference the base game text for help in this folder!

### Punctuation
To match the base game, Chinese translations use `。` instead of the periods used in English `.`.
The base game also uses `，` in place of `,` in Chinese.

### Keywords
On cards, keywords may need special handling. For example, in Chinese, we'll want a leading space before and afterthe keyword:

"Ethereal." becomes " 虚无 。" (note the extra spaces)

For custom keywords, please also keep the mod tag at the beginning: `ninetailed:Tail`.

### Special Characters
Some text uses special characters and keywords. The game expects it in certain formats depending on the language. For example:

- **Energy**: Displayed like [E]. `Gain [R] [R].` becomes `获得 [R] [R] 。` (with an extra space at before the period)
- **Dynamic numbers on cards**: Displayed like !this!.`Deal !D! damage.` becomes `造成 !D! 点伤害。` (!D! is still wrapped in spaces)

In other text like powers, numbers or keywords may be highlighted differently. For example, see this description for the After Image power:

```
"你每打出一张牌，得到 #b",
" 点 #y格挡 。"
```

`#b` is used right before we display a number.

`#y` is used right before a keyword we want to display. (If not tagged, it won't show up as a tooltip!)

### Lines
Generally, we're going to want to keep the same number of lines in the translated version as the English one. For example, NinshuPower here has 4 lines in the description. Ideally, the translated version would keep the same number of lines and order, so the numbers still go between:
```
"nineTailed:NinshuPower": {
    "NAME": "Ninshu",
    "DESCRIPTIONS": [
        "At the end of your turn, lose #b",
        " HP and shuffle #b",
        " random #yPower from any color into your draw pile.",
        " random #yPowers from any color into your draw pile."
    ]
},
```
This would (very roughly by AI) translate to something like this:
```
"nineTailed:NinshuPower"： {
 "NAME"： "Ninshu",
 "DESCRIPTIONS"： [
 " 在你的回合结束时，失去 #b",
 " HP 并将 #b",
 " 从任意颜色中随机抽取 #yPower 放入你的抽牌堆。",
 " 从任意颜色中随机抽取 #yPower 放入你的抽牌堆。"
    ]
},
```

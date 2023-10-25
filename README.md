# Minecraft Ultimate Announcer plugin

- UltimateAnnouncer 1.0.0
- Target Version: 1.20+
- Author: DrachenClon22
- Supported Languages: English, Russian

## Features
- Automatic calculation to declared date;
- Ability to add repeating events: every day or every month, or every year;
- Ability to add pauses between announces;
- Fully customizable bar event.

This plugin can be used on servers to create bar announces and timers to any event. Plugin automatically calculates remaining time to event and admin can fully customize config.yml to create custom bar announce.

## Examples of usage
```MiniYAML
this:
  title: "This is coming in &6{days} &r{day:s}"
  date: 31.12.X
  time: 7
```
In code above event "this" created, and bar will show remaining days ("{days}") to 31.12.X - where X is every year.
```MiniYAML
repeated:
  title: "This event will be in &6{days} &r{day:s}"
  date: 24.X.X
  time: 6
```
Event "repeated" will be repeated every 24th of every month, so timer will show countdown to next 24th of next month.

## Commands
Admin commands:
- `/announcer reload` - reloads plugin config.yml.

## Permissions
```MiniYAML
announcer.*:
  description: Allows to use all announcer commands
  default: op
  children:
    announcer.reload: true
announcer.reload:
  description: Allows to reload plugin config
  default: op
```

package org.mystic

class DotaPlayerBuilder {
  var name: String = ""
  var id: String = ""
  var kills: Int = 0
  var deaths: Int = 0
  var assists: Int = 0
  var lastHits: Int = 0
  var denies: Int = 0
  var gpm: Int = 0
  var towerKill: Int = 0
  var teamFightPercentage: Double = 0.0d
  var observerWardsPlaced: Int = 0
  var stacksMade: Int = 0
  var runesPickedUp: Int = 0
  var firstBloods: Int = 0

  def withName(name: String): DotaPlayerBuilder = {
    this.name = name
    this
  }

  def withID(id: String): DotaPlayerBuilder = {
    this.id = id
    this
  }

  def withKills(kills: Int): DotaPlayerBuilder = {
    this.kills = kills
    this
  }

  def withDeaths(deaths: Int): DotaPlayerBuilder = {
    this.deaths = deaths
    this
  }

  def withAssists(assists: Int): DotaPlayerBuilder = {
    this.assists = assists
    this
  }

  def withLastHits(lastHits: Int): DotaPlayerBuilder = {
    this.lastHits = lastHits
    this
  }

  def withDenies(denies: Int): DotaPlayerBuilder = {
    this.denies = denies
    this
  }

  def withGpm(gpm: Int): DotaPlayerBuilder = {
    this.gpm = gpm
    this
  }

  def withTowerKill(towerKill: Int): DotaPlayerBuilder = {
    this.towerKill = towerKill
    this
  }

  def withTeamFightPercentage(teamFightPercentage: Double): DotaPlayerBuilder = {
    this.teamFightPercentage = teamFightPercentage
    this
  }

  def withObservers(observerWardsPlaced: Int): DotaPlayerBuilder = {
    this.observerWardsPlaced = observerWardsPlaced
    this
  }

  def withStacks(stacksMade: Int): DotaPlayerBuilder = {
    this.stacksMade = stacksMade
    this
  }

  def withRunes(runesPickedUp: Int): DotaPlayerBuilder = {
    this.runesPickedUp = runesPickedUp
    this
  }

  def withFB(firstBloods: Int): DotaPlayerBuilder = {
    this.firstBloods = firstBloods
    this
  }


  def build: DotaPlayer = new DotaPlayer(this.name,
    this.id,
    this.kills,
    this.deaths,
    this.assists,
    this.lastHits,
    this.denies,
    this.gpm,
    this.towerKill,
    this.teamFightPercentage,
    this.observerWardsPlaced,
    this.stacksMade,
    this.runesPickedUp,
    this.firstBloods)
}

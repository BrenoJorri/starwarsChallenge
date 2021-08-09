package com.trivago.mapper.favorite

import com.trivago.core.base.BaseMapper
import com.trivago.domain.favorites.model.FavoriteCharacterDomain
import com.trivago.local.entity.FavoriteCharacterEntity

class FavoritesCharacterMapper: BaseMapper.ToEntity<FavoriteCharacterEntity, FavoriteCharacterDomain> {

    override fun mapToEntity(domain: FavoriteCharacterDomain): FavoriteCharacterEntity =
        FavoriteCharacterEntity(domain.name ?:  "")

}

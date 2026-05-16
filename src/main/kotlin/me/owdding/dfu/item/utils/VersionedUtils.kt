package me.owdding.dfu.item.utils

import com.google.common.collect.Multimap
import com.mojang.authlib.properties.Property
import com.mojang.authlib.properties.PropertyMap
import java.util.UUID
import net.minecraft.world.item.component.ResolvableProfile
import com.google.common.collect.LinkedHashMultimap
import com.mojang.authlib.GameProfile


internal fun createPropertyMap(init: Multimap<String, Property>.() -> Unit): PropertyMap = PropertyMap(LinkedHashMultimap.create<String, Property>().apply(init))
internal fun createResolvableProfile(name: String, uuid: UUID, properties: PropertyMap) = ResolvableProfile.createResolved(GameProfile(uuid, name, properties))
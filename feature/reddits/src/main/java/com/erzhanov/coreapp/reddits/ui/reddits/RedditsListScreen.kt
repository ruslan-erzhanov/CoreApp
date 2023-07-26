package com.erzhanov.coreapp.reddits.ui.reddits

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.erzhanov.coreapp.core.base.R
import com.erzhanov.coreapp.core.base.ui.components.bindWithLifecycle
import com.erzhanov.coreapp.core.base.ui.model.PlaceHolderItem
import com.erzhanov.coreapp.core.base.ui.model.ProgressItem
import com.erzhanov.coreapp.core.base.ui.theme.customColors
import com.erzhanov.coreapp.core.base.ui.theme.headlineXSmall
import com.erzhanov.coreapp.data.common.asTimeDefString
import com.erzhanov.coreapp.reddits.model.AppReddit
import com.erzhanov.coreapp.reddits.model.RedditListEffect
import com.erzhanov.coreapp.reddits.model.RedditListIntent
import com.erzhanov.coreapp.reddits.model.RedditListState
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RedditsListScreen(
    state: RedditListState,
    intents: (RedditListIntent) -> Unit,
    effects: Flow<RedditListEffect>,
    imageLoader: ImageLoader,
    savedOnly: Boolean
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        intents(RedditListIntent.GetInitialItems(savedOnly))
    }

    effects.bindWithLifecycle {
        when (it) {
            RedditListEffect.OnError -> Toast.makeText(context, R.string.error_list_msg, Toast.LENGTH_LONG).show()
        }
    }

    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.customColors.bgModal)
        ,
        contentPadding = PaddingValues(bottom = 16.dp),
    ) {

        itemsIndexed(state.items, key = { _, item -> item.id }) { index, item ->
            if (index >= state.items.size - 1 && state.hasNext && !state.inProgress) {
                intents(RedditListIntent.GetNextItems(state.nextPageToken))
            }
            when (item) {
                is AppReddit -> RedditItemCard(imageLoader, item, intents, Modifier.animateItemPlacement())
                is ProgressItem -> RedditProgressCard(Modifier.animateItemPlacement())
                is PlaceHolderItem.Empty -> PlaceHolderItemContent(item)
                is PlaceHolderItem.Error -> PlaceHolderItemContent(item) {
                    intents(RedditListIntent.GetInitialItems(savedOnly))
                }
            }
        }
    }
}

@Composable
fun RedditItemCard(
    imageLoader: ImageLoader,
    item: AppReddit,
    intents: (RedditListIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(MaterialTheme.customColors.cellSecondary),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            val placeholder = painterResource(id = R.drawable.reddit_item_placeholder)
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.thumbnail)
                    .crossfade(true)
                    .transformations(RoundedCornersTransformation(with(LocalDensity.current) { 24.dp.toPx() }))
                    .build(),
                imageLoader = imageLoader,
                placeholder = placeholder,
                fallback = placeholder
            )
            Row(
                modifier = Modifier,
            ) {
                Image(
                    painter = painter,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(88.dp),
                    contentDescription = "",
                )
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp, end = 16.dp),
                ) {
                    Text(
                        modifier = Modifier,
                        text = item.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = MaterialTheme.customColors.labelPrimary
                        )
                    )

                    Text(
                        modifier = Modifier.padding(top = 6.dp, end = 16.dp),
                        text = "Author: ${item.author}",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.customColors.labelPrimary
                        ),
                    )

                    Text(
                        modifier = Modifier.padding(top = 6.dp, end = 16.dp),
                        text = "Date: ${item.created.asTimeDefString}",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.customColors.labelPrimary
                        )
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(.8f)
                        .align(Alignment.BottomStart),
                    text = item.url,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.customColors.labelPrimary
                    ),
                )
                
                IconButton(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    onClick = {
                        val intent = when {
                            item.saved -> RedditListIntent.RemoveReddit(item)
                            else -> RedditListIntent.SaveReddit(item)
                        }
                        intents(intent)
                    }
                ) {
                    Icon(
                        Icons.Filled.Favorite,
                        "contentDescription",
                        tint = MaterialTheme.customColors.labelBrand.takeIf { item.saved } ?: MaterialTheme.customColors.bgModal
                    )
                }
            }
        }
    }
}

@Composable
fun PlaceHolderItemContent(
    item: PlaceHolderItem,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = item.getImage(isSystemInDarkTheme().not())),
            modifier = Modifier
                .padding(16.dp)
                .size(198.dp),
            contentDescription = "",
        )

        Text(
            modifier = Modifier.width(262.dp),
            maxLines = 2,
            textAlign = TextAlign.Center,
            text = stringResource(item.textId),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.customColors.labelPrimary
            ),
        )

        if (item is PlaceHolderItem.Error) {
            TextButton(
                onClick = onClick,
                modifier = Modifier
                    .padding(top = 4.dp),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.customColors.labelPrimary
                ),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
                shape = RoundedCornerShape(13.dp)
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(item.btnTextId),
                    style = MaterialTheme.typography.headlineXSmall
                )
            }
        }
    }
}

@Composable
fun RedditProgressCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.customColors.cellSecondary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier,
            ) {
                Box(
                    modifier = Modifier
                        .shimmer()
                        .padding(16.dp)
                        .size(88.dp)
                        .background(color = MaterialTheme.customColors.iconTertiary, shape = RoundedCornerShape(24.dp))
                )

                Column(
                    modifier = Modifier
                        .padding(top = 16.dp, end = 16.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .shimmer()
                            .padding(top = 6.dp)
                            .size(220.dp, 16.dp)
                            .background(color = MaterialTheme.customColors.iconTertiary, shape = RoundedCornerShape(16.dp))
                    )
                    Box(
                        modifier = Modifier
                            .shimmer()
                            .padding(top = 6.dp)
                            .size(220.dp, 16.dp)
                            .background(color = MaterialTheme.customColors.iconTertiary, shape = RoundedCornerShape(16.dp))
                    )

                    Row(
                        modifier = Modifier
                            .padding(top = 12.dp, end = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .shimmer()
                                .size(16.dp)
                                .background(color = MaterialTheme.customColors.iconTertiary, shape = RoundedCornerShape(16.dp))
                        )

                        Box(
                            modifier = Modifier
                                .shimmer()
                                .padding(start = 8.dp, end = 16.dp)
                                .size(120.dp, 14.dp)
                                .background(color = MaterialTheme.customColors.iconTertiary, shape = RoundedCornerShape(16.dp))
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 16.dp),
                verticalAlignment = Alignment.Bottom
            ) {

                Box(
                    modifier = Modifier
                        .shimmer()
                        .size(40.dp, 16.dp)
                        .background(color = MaterialTheme.customColors.iconTertiary, shape = RoundedCornerShape(16.dp))
                )

                Box(
                    modifier = Modifier
                        .shimmer()
                        .padding(start = 20.dp)
                        .size(40.dp, 16.dp)
                        .background(color = MaterialTheme.customColors.iconTertiary, shape = RoundedCornerShape(16.dp))
                )

                Box(
                    modifier = Modifier
                        .shimmer()
                        .padding(start = 20.dp)
                        .size(40.dp, 16.dp)
                        .background(color = MaterialTheme.customColors.iconTertiary, shape = RoundedCornerShape(16.dp))
                )
            }
        }
    }
}
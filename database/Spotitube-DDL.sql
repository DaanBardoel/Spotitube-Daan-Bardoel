USE [Spotitube]
GO

/****** Object:  Table [dbo].[Account]    Script Date: 23/10/2018 11:20:39 ******/
CREATE TABLE [dbo].[Account] (
  [userID]   [int] IDENTITY (1, 1) NOT NULL,
  [user]     [nvarchar](50)        NOT NULL,
  [password] [nvarchar](255)       NOT NULL,
  CONSTRAINT [PK_Account] PRIMARY KEY NONCLUSTERED ([userID] ASC)
)
GO

/****** Object:  Table [dbo].[Playlistcontent]    Script Date: 23/10/2018 11:20:39 ******/
CREATE TABLE [dbo].[Playlistcontent] (
  [playlistID]          [int] NOT NULL,
  [trackID]             [int] NOT NULL,
  [offlineAvailability] [bit] NOT NULL,
  CONSTRAINT [PK_playlistcontent] PRIMARY KEY NONCLUSTERED ([playlistID] ASC, [trackID] ASC)
)
GO

/****** Object:  Table [dbo].[Playlists]    Script Date: 23/10/2018 11:20:39 ******/
CREATE TABLE [dbo].[Playlists] (
  [playlistID]   [int] IDENTITY (1, 1) NOT NULL,
  [playlistname] [nvarchar](50)        NOT NULL,
  [ownerID]      [int]                 NOT NULL,
  CONSTRAINT [PK_Playlists] PRIMARY KEY NONCLUSTERED ([playlistID] ASC)
)
GO

/****** Object:  Table [dbo].[TokenDTO]    Script Date: 23/10/2018 11:20:39 ******/
CREATE TABLE [dbo].[Token] (
  [userID]             [int]          NOT NULL,
  [tokenOnlyForReturn] [nvarchar](50) NOT NULL,
  [validUntil]         [nvarchar](50) NOT NULL,
  CONSTRAINT [PK_Token] PRIMARY KEY NONCLUSTERED ([userID] ASC)
)
GO

/****** Object:  Table [dbo].[Tracks]    Script Date: 23/10/2018 11:20:39 ******/
CREATE TABLE [dbo].[Tracks] (
  [numberID]         [int] IDENTITY (1, 1) NOT NULL,
  [title]            [nvarchar](255)       NOT NULL,
  [performer]        [nvarchar](50)        NOT NULL,
  [duration]         [int]                 NOT NULL,
  [album]            [nvarchar](50)        NOT NULL,
  [playcount]        [int]                 NOT NULL,
  [publicationDate]  [nvarchar](50)        NOT NULL,
  [description]      [nvarchar](255)       NOT NULL,

  CONSTRAINT [PK_Tracks] PRIMARY KEY NONCLUSTERED ([numberID] ASC)
)
GO

/*foreign key constraints for ensuring correct data that exists in Playlistcontent*/
ALTER TABLE [dbo].[Playlistcontent]
  ADD CONSTRAINT [FK_Playlistcontent_playlistID_Playlists_playlistID] FOREIGN KEY ([playlistID])
REFERENCES [dbo].[Playlists] ([playlistID])
  ON UPDATE CASCADE
  ON DELETE CASCADE
GO

ALTER TABLE [dbo].[Playlistcontent]
  ADD CONSTRAINT [FK_Playlistcontent_trackID_Tracks_numberID] FOREIGN KEY ([trackID])
REFERENCES [dbo].[Tracks] ([numberID])
  ON UPDATE CASCADE
  ON DELETE CASCADE
GO

/*foreign key constraint for ensuring that ownerID exists as userID in Account*/
ALTER TABLE [dbo].[Playlists]
  ADD CONSTRAINT [FK__Playlists__ownerID_Account_userID] FOREIGN KEY ([ownerID])
REFERENCES [dbo].[Account] ([userID])
  ON UPDATE CASCADE
  ON DELETE CASCADE
GO

/*foreign key constraint for ensuring that userID exists in Account*/
ALTER TABLE [dbo].[Token]
  ADD CONSTRAINT [FK__Token__userID] FOREIGN KEY ([userID])
REFERENCES [dbo].[Account] ([userID])
  ON UPDATE CASCADE
  ON DELETE CASCADE
GO